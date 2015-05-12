/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.pnaika.fp.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author spyrisos
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

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

        request.logout();
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Logout</title>");  
            out.println("<style>"+
                     "header{\n" +
                        "text-align: left;\n" +
                       "font-family: 'Lora', serif;\n" +
                        "width: auto;\n" +
                        "height:auto;\n" +
                        "font-size: 35px;\n" +
                        "padding: 0 15px 0 15px;\n" +
                        "border-color: black;\n" +
                        "}"
                    +"*{"+
                         "box-sizing: border-box;"+
                    "}"
                    +"p{\n" +
                        "font-family: 'Lora', serif;\n" +
                        "text-align: center;\n" +
                        "font-size: large;\n" +
                    "}"
                    +"a:hover{color:red;}"
                    +".table{"+
                        "margin:15px;"+
                    "}"+
                    "h3{"+
                        "margin:10px;"+
                    "}"
                    + "</style>"
                    +"<link href='http://fonts.googleapis.com/css?family=Lora:400,700' rel='stylesheet' type='text/css'>"+
                     "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css\">"
                    +"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css\">"+
                     "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js\"></script>"   );
            out.println("</head>");
            out.println("<body>");
            out.println("<br>");
            out.println("<header>You are logged out! </header>");
            out.println("<p>CLICK HERE, To <a href=\"" + request.getContextPath() + "/customerPortal/\">Login</a>"+" as a Customer </p>");
            out.println("<p>CLICK HERE, To <a href=\"" + request.getContextPath() + "/employeePortal/\">Login</a>"+" as an Employee </p>");
            out.println("<br>");
            out.println("<br>");    
            out.println("<a href=\"/pnaika-mp4/site/apidocs/index.html\"><strong>Please click here to see <u> JAVA DOC</u></strong></a>");
            out.println("</body>");
            out.println("</html>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
