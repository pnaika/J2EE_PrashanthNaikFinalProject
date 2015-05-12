/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.pnaika.fp.web;


import edu.iit.sat.itmd4515.pnaika.fp.domain.Address;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Customer;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Employee;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.AddressService;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.CustomerService;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.EmployeeService;
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

@WebServlet(name = "EmployeePortalServlet", urlPatterns = {"/employeePortal","/employeePortal/"})
public class EmployeePortalServlet extends HttpServlet {

    @EJB
    private EmployeeService employeeService;
    
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Employee Portal</title>");
            out.println("<style>"+
                     "header{\n" +
                        "text-align: left;\n" +
                        "font-family: 'Lora', serif;\n" +
                        "width: auto;\n" +
                        "height:auto;\n" +
                        "font-size: 30px;\n" +
                        "padding: 0 15px 0 15px;\n" +
                        "border-color: black;\n" +
                        "}"
                    +"*{"+
                         "box-sizing: border-box;"+
                    "}"
                    +"thead{\n" +
                        "font-weight: bold;" +
                    "}"
                    +".table{"+
                        "margin:15px;"+
                    "}"+
                    "h3{"+
                        "margin:10px;"+
                    "}"+
                    "</style>"
                    +"<link href='http://fonts.googleapis.com/css?family=Lora:400,700' rel='stylesheet' type='text/css'>"+
                     "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css\">"
                    +"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css\">"+
                     "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js\"></script>"   );

                                
            out.println("</head>");
            out.println("<body>");
            
            if (request.isUserInRole("employee")) {                
                Employee e = employeeService.findByUsername(request.getRemoteUser());               
                out.println("<br>");
                out.println("<header> Hello " + e.getEmp_name() +"</header>");
                out.println("<br>");
                out.println("<h3>Please find all the customer details below</h3>");
                List<Customer> c1 = customerService.findAll();
                List<Employee> e1 = employeeService.findAll(); 
                out.println("<br>");
                out.println("<table class='table'> <thead><tr> <td width ='50'>"
                             + "ID" + "</td> <td width='150'> "
                             + "Customer Name"+"</td> <td width='100'> "
                             + "Contact no"+"</td> <td width='150'> "
                             + "Customer Email ID"+" </td> <td width ='100'>  "
                             + "City"+"</td> <td width ='100'> "
                             + "State"+" </td> <td width ='100'> "
                             + "Zip Code"+" </td> <td width ='100'> "
                             + "Address Type"+" </td>  "
                             +"</tr></thead></table>");
                for (int i = 0;i <= c1.size() - 1;i++){
                    Address a1 = addressService.find(c1.get(i).getAddress().getAddress_id());
                    out.println("<table class='table table-hover'> <tbody><tr> <td width='50'>"
                             + c1.get(i).getCust_id() + "</td> <td width='150'> "
                             + c1.get(i).getCust_name()+"</td> <td width='100'> "
                             + c1.get(i).getCust_phone()+"</td> <td width='150'> "
                             + c1.get(i).getCust_email()+" </td> <td width ='100'> "
                             + a1.getAddress_city()+"</td> <td width ='100'> "
                             + a1.getAddress_state()+"</td> <td width ='100'> "
                             + a1.getAddress_zip()+"</td> <td width ='100'> "
                                 + a1.getAddress_type()+"</td> "
                             +"</tr></tbody></table>");
                }
                out.println("<br>");
                out.println("<h3>Please find all the employee details below</h3>");
                out.println("<br>");
                out.println("<table class='table'> <thead><tr> <td width ='50'>"
                             + "ID" + "</td> <td width='150'> "
                             + "Employee Name"+"</td> <td width='100'> "
                             + "Contact no"+"</td> <td width='150'> "
                             + "Employee Email ID"+" </td> <td width ='100'>  "
                             + "City"+"</td> <td width ='100'> "
                             + "State"+" </td> <td width ='100'> "
                             + "Zip Code"+" </td> <td width ='100'> "
                             + "Address Type"+" </td>   "
                             +"</tr></thead></table>");
                for (int i = 0;i <= c1.size() - 1;i++){
                    Address a = addressService.find(e1.get(i).getAddress().getAddress_id());
                    out.println("<table class='table'> <tbody><tr> <td width='50'>"
                             + e1.get(i).getEmp_id() +"</td> <td width='150'>"
                             + e1.get(i).getEmp_name() + "</td> <td width='100'> "
                             + e1.get(i).getEmp_phone()+"</td> <td width='150'> "
                             + e1.get(i).getEmp_email()+"</td> <td width ='100'> "
                             + a.getAddress_city()+"</td> <td width ='100'> "
                             + a.getAddress_state()+"</td> <td width ='100'> "
                             + a.getAddress_zip()+"</td> <td width ='100'> "
                             + a.getAddress_type()+"</td> "
                             +"</tr></tbody></table>");
                }
                out.println("<br>");
                out.println("<br>");
            }
            
            out.println("<a href=\"" + request.getContextPath() + "/logout\">Logout</a>");
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
