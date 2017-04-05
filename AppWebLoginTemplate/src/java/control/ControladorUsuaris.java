
package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AccesUsuaris;
import model.Usuari;


/**
 *
 * @author montse
 * @version març 2017
 */
public class ControladorUsuaris extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            AccesUsuaris bd = new AccesUsuaris();
            String op = request.getParameter("accio");
            if (op.equals("login")) {
                System.out.println("Validando sin password llega al servlet?");
                Usuari u = (Usuari) request.getAttribute("usu");
                System.out.println("Usuari en servlet, login="+u);
                String nom=bd.validarUsuari(u);
                //passo el nom a la vista, benvinguda.jsp
                request.setAttribute("nom", nom);
                RequestDispatcher rd = request.getRequestDispatcher("benvinguda.jsp");
                rd.forward(request, response);
            } else {
                if (op.equals("registre")) {
                    Usuari u = (Usuari) request.getAttribute("usu");
                    bd.registrarUsuari(u);
                    //només passo el nom a la vista, a benvinguda.jsp
                    request.setAttribute("nom",u.getNom());
                    RequestDispatcher rd = request.getRequestDispatcher("benvinguda.jsp");
                    rd.forward(request, response);
                }
            }
        } catch (Exception e) {
            System.out.println("CONTROLADOR USUARIS!!!!!!" + e);
            throw new ServletException(e);
            // System.out.println(e);
        }
    }
        // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
        /**
         * Handles the HTTP <code>GET</code> method.
         *
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        @Override
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Handles the HTTP <code>POST</code> method.
         *
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        @Override
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    
}
