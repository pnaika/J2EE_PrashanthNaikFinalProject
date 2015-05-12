/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.pnaika.fp.web;

import edu.iit.sat.itmd4515.pnaika.fb.bean.EmployeeBean;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Address;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Customer;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Employee;
import edu.iit.sat.itmd4515.pnaika.fp.domain.security.Group;
import edu.iit.sat.itmd4515.pnaika.fp.domain.security.User;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.AddressService;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.CustomerService;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.EmployeeService;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.GroupService;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.UserService;
import java.text.ParseException;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Prashanth
 */
@Named
@RequestScoped
public class CommonController extends AbstractJSFBean{
    
    @EJB
    private CustomerService customerService;
    
    @EJB
    private EmployeeService employeeService;
    
    @EJB
    private UserService userService;
    
    @EJB
    private GroupService groupService;
    
    @EJB
    private AddressService addressService;
    
    @Inject 
    private LoginBean loginBean;
    
    
    private Customer customer;
    private Email email;
    private String cust_name;    
    private String cust_phone;    
    private String cust_email;
    
    private String emp_name;    
    private String emp_phone;    
    private String emp_email;

    private String address_type; 
    private String address_city;    
    private String address_state;
    private String address_zip;
    
    private String custUserName;
    private String custpassword;
    private String empUserName;
    private String emppassword;
    

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public GroupService getGroupService() {
        return groupService;
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    public AddressService getAddressService() {
        return addressService;
    }

    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCust_phone() {
        return cust_phone;
    }

    public void setCust_phone(String cust_phone) {
        this.cust_phone = cust_phone;
    }

    public String getCust_email() {
        return cust_email;
    }

    public void setCust_email(String cust_email) {
        this.cust_email = cust_email;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_phone() {
        return emp_phone;
    }

    public void setEmp_phone(String emp_phone) {
        this.emp_phone = emp_phone;
    }

    public String getEmp_email() {
        return emp_email;
    }

    public void setEmp_email(String emp_email) {
        this.emp_email = emp_email;
    }

    public String getAddress_type() {
        return address_type;
    }

    public void setAddress_type(String address_type) {
        this.address_type = address_type;
    }

    public String getAddress_city() {
        return address_city;
    }

    public void setAddress_city(String address_city) {
        this.address_city = address_city;
    }

    public String getAddress_state() {
        return address_state;
    }

    public void setAddress_state(String address_state) {
        this.address_state = address_state;
    }

    public String getAddress_zip() {
        return address_zip;
    }

    public void setAddress_zip(String address_zip) {
        this.address_zip = address_zip;
    }

    public String getCustpassword() {
        return custpassword;
    }

    public void setCustpassword(String custpassword) {
        this.custpassword = custpassword;
    }

    public String getEmppassword() {
        return emppassword;
    }

    public void setEmppassword(String emppassword) {
        this.emppassword = emppassword;
    }

    public String getCustUserName() {
        return custUserName;
    }

    public void setCustUserName(String custUserName) {
        this.custUserName = custUserName;
    }

    public String getEmpUserName() {
        return empUserName;
    }

    public void setEmpUserName(String empUserName) {
        this.empUserName = empUserName;
    }
    
    
    public CommonController() {
        super.postContructor();
    }
    
    public String Signup() {
        return "/common/signup.xhtml";
    }
    
    
    public String createNewEmployee() throws ParseException {
        // faceContext.addMessage(null, new FacesMessage("Username Name : " + advusername + " Exist"));
        User user = userService.find(empUserName);

        if (user != null) {
            facesContext.addMessage(null, new FacesMessage("Username Name : " + empUserName + " Exist"));
            return "/common/signup.xhtml";
        } else {

            Group g1 = new Group("Employees", "Group of Employees");
            user = new User(getEmpUserName(), getEmppassword());
            
            user.addGroup(g1);
            userService.create(user);
            
            Employee emp = new Employee(emp_name, emp_phone, emp_email);
            Address adds = new Address("E", address_city, address_state, address_zip);
            
            addressService.create(adds);
            
            emp.setUser(user);
            emp.setAddress(adds);
            
            employeeService.create(emp);
            
            
            email = new Email(emp_email) ;
            
            facesContext.addMessage(null, new FacesMessage("Employee Registration was successful and an Email is sent to your email ID!!! You can Login now"));
            return "/login.xhtml";
        }
    }
    
    public String createNewCustomer() throws ParseException {
        // faceContext.addMessage(null, new FacesMessage("Username Name : " + advusername + " Exist"));
        User user = userService.find(custUserName);

        if (user != null) {
            facesContext.addMessage(null, new FacesMessage("Username Name : " + custUserName + " Exist"));
            //return "/common/signup.xhtml?faces-redirect=true";
            return "/common/customersignup.xhtml";
        } else {

            Group g2 = new Group("Customers", "Group of Customers");
            user = new User(getCustUserName(), getCustpassword());
            
            user.addGroup(g2);
            userService.create(user);

            Customer custNew = new Customer(cust_name, cust_phone, cust_email);
            Address adds = new Address("C", address_city, address_state, address_zip);
            
            custNew.setUser(user);
            addressService.create(adds);
            
            custNew.setUser(user);
            custNew.setAddress(adds);
            
            customerService.create(custNew);
            
            email = new Email(cust_email) ;
            
            facesContext.addMessage(null, new FacesMessage("Customer Registration was successful and an Email is sent to your email ID!!! Click on Login link to Login"));
            //  return "/index.xhtml?faces-redirect=true";
            return "/login.xhtml";
        }
    }
}
