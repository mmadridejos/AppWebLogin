package model;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe que gestiona la connexió a un SGBDR MySQL La taula on es guarden les
 * dades es diu t_usuaris i les columnes id varchar(20(), password varchar(100),
 * nom varchar(45) Utilizo una funció per encriptar els passwords
 *
 * @author montse
 * @version abril 2017
 */
public class AccesUsuaris {

    private Connection con;
    private Statement sentencia;

    public AccesUsuaris() throws Exception {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("1234");
       // dataSource.setServerName("elnomdelserver.org");
        dataSource.setDatabaseName("bd_proves");
        con=dataSource.getConnection();
        sentencia=con.createStatement();
    }
//    public AccesUsuaris() throws Exception {
//
//        Class.forName("com.mysql.jdbc.Driver"); 
//        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_proves?useUnicode=yes&characterEncoding=UTF-8", "root", "1234");
//        sentencia = con.createStatement();
//    }

    //retorna el nom de l'usuari valid
    public String validarUsuari(Usuari u) throws UsuariInexistentException, PasswordIncorrecteException, SQLException {
        //filtrar getNom() d'entrada "maliciosa"        
        String text = "select * from t_usuaris where id='" + u.getId() + "'";
        ResultSet res = sentencia.executeQuery(text);
        String p2 = getEncryptedPassword(u.getPassword());
        if (res.next()) { /* si hi ha un registre*/
            /* si coincideix la clau*/

            if (!res.getString("password").equals(p2)) {
                throw new PasswordIncorrecteException();
            } else {
                return res.getString("nom");
            }
        } else {
            throw new UsuariInexistentException(); /* no hi ha registre, per tant, no hi ha usuari*/

        }
    }

    /* donar d'alta un nou usuari*/
    public void registrarUsuari(Usuari u) throws UsuariRepetitException {
        try {
            String p2 = getEncryptedPassword(u.getPassword());
            String text = "insert into t_usuaris(id, password, nom) values ('" + u.getId() + "','" + p2 + "','" + u.getNom() + "')";
            sentencia.executeUpdate(text);
        } catch (SQLException e) {
            System.out.println(e);
            throw new UsuariRepetitException();
        }
    }

    /**
     * Encrypt password by using SHA-256 algorithm, encryptedPassword length is
     * 32 bits
     *
     * @param clearTextPassword
     * @return
     * @throws NoSuchAlgorithmException reference
     * http://java.sun.com/j2se/1.4.2/docs/api/java/security/MessageDigest.html
     * @version per java 8 ja existeix java.util.Base64
     */
    public static String getEncryptedPassword(String clearTextPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(clearTextPassword.getBytes());
            //aquesta funció s'ha de canviar per un Base64 oficial de java.util.Base64
            return new sun.misc.BASE64Encoder().encode(md.digest());
        } catch (NoSuchAlgorithmException e) {
            //_log.error("Failed to encrypt password.", e);
        }
        return "";
    }
}
