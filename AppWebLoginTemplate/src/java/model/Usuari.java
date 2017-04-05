package model;

/**
 *
 * @author montse
 */
import java.io.Serializable;

/**
 *
 * @author montse
 * @version abril 2017
 */
public class Usuari implements Serializable {

    private String id;
    private String password;
    private String nom;

    public Usuari(String id, String password, String n) {
        this.id = id;
        this.password = password;
        nom=n;
    }

    public Usuari() {

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //sense el password
    @Override
    public String toString() {
        return "Usuari{" + "id=" + id + ", nom=" + nom + ", password="+password+'}';
        
    }
}

