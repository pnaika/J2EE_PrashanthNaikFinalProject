/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.pnaika.fb.bean;

import edu.iit.sat.itmd4515.pnaika.fp.domain.Address;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Employee;
import edu.iit.sat.itmd4515.pnaika.fp.domain.security.Group;
import edu.iit.sat.itmd4515.pnaika.fp.domain.security.User;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.AddressService;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.EmployeeService;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.UserService;
import edu.iit.sat.itmd4515.pnaika.fp.web.AbstractJSFBean;
import edu.iit.sat.itmd4515.pnaika.fp.web.LoginBean;
import java.text.ParseException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Prashanth
 */

@Named(value = "employeeUpdateBean")
@RequestScoped
public class EmployeeUpdateBean extends AbstractJSFBean{
    
    @EJB
    private UserService userService;
    
    @EJB
    private EmployeeService employeeService;
    
    @EJB
    private AddressService addressService;
    
    @Inject
    private LoginBean loginBean;
    
    private Employee employee;
    private Address address;
    private User user;

    public EmployeeUpdateBean() {
        super.postContructor();
    }

    @PostConstruct
    private void postConstruct() {
        super.postContructor();
        employee = new Employee();
        employee.setAddress(new Address());
        employee.setUser(new User());
        employee = employeeService.findByUsername(loginBean.getRemoteUser());   
    }
    
    public String saveEmployee() throws ParseException{
        
        User userName = userService.find(employee.getUser().getUserName());
        
        if(userName != null){
            facesContext.addMessage(null, new FacesMessage("User Name   " +employee.getUser().getUserName() +" is already present!"));
            return "/employeePortal/updateProfile.xhtml";   
        }else{
            Group g1 = new Group("Employees", "Group of Employees");
            user = new User(employee.getUser().getUserName(), employee.getUser().getPassword());
            
            user.addGroup(g1);
            userService.update(user);
            
            employeeService.update(employee);
            addressService.update(employee.getAddress());
            facesContext.addMessage(null, new FacesMessage("Employee updation is complete !!!, Thank you."));
        }
        return "/employeePortal/welcome.xhtml";
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public FacesContext getFacesContext() {
        return facesContext;
    }

    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }

    public Flash getFlash() {
        return flash;
    }

    public void setFlash(Flash flash) {
        this.flash = flash;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
