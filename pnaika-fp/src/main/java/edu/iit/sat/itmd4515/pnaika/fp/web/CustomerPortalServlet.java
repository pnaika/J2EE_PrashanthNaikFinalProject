/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.pnaika.fp.web;


import edu.iit.sat.itmd4515.pnaika.fp.domain.Address;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Customer;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.AddressService;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.CustomerService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Prashanth
 */

@WebServlet(name = "CustomerPortalServlet", urlPatterns = {"/customerPortal",
                                                           "/customerPortal/",
                                                           "/readCustomer"})
public class CustomerPortalServlet extends HttpServlet {

    @EJB
    private CustomerService customerService;
    
    @EJB
    private AddressService addressService;
    
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Customer Portal</title>");            
//            out.println("<style>"
//                         +"header{\n" +
//                        "text-align: left;\n" +
//                        "font-family: 'Lora', serif;\n" +
//                        "width: auto;\n" +
//                        "height:auto;\n" +
//                        "font-size: 25px;\n" +
//                        "padding: 0 15px 0 15px;\n" +
//                        "border-color: black;\n" +
//                        
//                        "}"
//                    +"*{"+
//                         "box-sizing: border-box;"+
//                    "}"
//                    +".table{"+
//                        "margin:15px;"+
//                    "}"+
//                    
//                    "thead{\n" +
//                        "font-weight: bold;" +
//                    "}"
//                    +"h3{"+
//                        "margin:10px;"+
//                    "}"
//                    + "</style>");
//            
//            out.println("</head>");
//            out.println("<body>");
//            
//            
//            if (request.isUserInRole("customer")) {                
//                Customer c = customerService.findByUsername(request.getRemoteUser());
//                out.println("<br>");
//                out.println("<header> Hi " + c.getCust_name() +", Please find your details.</header>");
//                out.println("<br>");
//                out.println("<br>");
//                out.println("<hr>");
//                out.println("<br>");
//                Address a = addressService.find(c.getAddress().getAddress_id());
//                
//                out.println("<table class='table'> <thead><tr> <td width ='50'>"
//                             + "ID" + "</td> <td width='150'> "
//                             + "Customer Name"+"</td> <td width='100'> "
//                             + "Contact no"+"</td> <td width='200'> "
//                             + "Customer Email ID"+" </td> <td width ='100'>  "
//                             + "City"+"</td> <td width ='100'> "
//                             + "State"+" </td> <td width ='100'> "
//                             + "Zip Code"+" </td> <td width ='100'> "
//                             + "Address Type"+" </td>  "
//                             +"</tr></thead></table>");
//                out.println("<table class='table table-hover'> <tbody><tr> <td width ='50'>"
//                             + c.getCust_id() + "</td> <td width='150'> "
//                             + c.getCust_name()+"</td> <td width='100'> "
//                             + c.getCust_phone()+"</td> <td width='200'> "
//                             + c.getCust_email()+" </td> <td width ='100'> "
//                             + a.getAddress_city()+"</td> <td width ='100'> "
//                             + a.getAddress_state()+"</td> <td width ='100'> "
//                             + a.getAddress_zip()+"</td> <td width ='100'> "
//                             + a.getAddress_type()+"</td> "
//                             +"</tr></tbody></table>");
//                out.println("<br>");
//                out.println("<br>");
//            }
//            
//            out.println("<a href=\"" + request.getContextPath() + "/logout\">Logout</a>"+"\n");
//            out.println("<br>");
//            out.println("<br>");
//            out.println("<a href=\"/pnaika-fp/site/apidocs/index.html\"><strong>Please click here to see <u> JAVA DOC</u></strong></a>");
//            out.println("</body>");
//            out.println("</html>");
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
//        switch(request.getServletPath()){
//            case "/readCustomer" :
//                    
//                request.setAttribute("customer", customerService.findAll());
//                request.getRequestDispatcher("/WEB-INF/pages/readCustomer.jsp").forward(request, response);
//                break;
//        }
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
