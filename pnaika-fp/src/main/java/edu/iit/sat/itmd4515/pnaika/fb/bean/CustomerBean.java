/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.pnaika.fb.bean;

import edu.iit.sat.itmd4515.pnaika.fp.domain.Address;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Customer;
import edu.iit.sat.itmd4515.pnaika.fp.domain.security.Group;
import edu.iit.sat.itmd4515.pnaika.fp.domain.security.User;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.AddressService;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.CustomerService;
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

@Named(value = "customerBean")
@RequestScoped
public class CustomerBean extends AbstractJSFBean{
    
    @EJB
    private UserService userService;
    
    @EJB
    private CustomerService customerService;
    
    @EJB
    private AddressService addressService;
    
    @Inject
    private LoginBean loginBean;
    
    private Customer customer;
    private Address address;
    private User user;

    public CustomerBean() {
        super.postContructor();
    }

    @PostConstruct
    private void postConstruct() {
        super.postContructor();
        customer = new Customer();
        customer.setAddress(new Address());
        customer.setUser(new User());
        customer = customerService.findByUsername(loginBean.getRemoteUser());   
    }
    
    public String saveCustomer() throws ParseException{
        
        User userName = userService.find(customer.getUser().getUserName());
        
        if(userName != null){            
            facesContext.addMessage(null, new FacesMessage("User Name " +customer.getUser().getUserName() +" is already present!"));
            return "/customerPortal/updateProfile.xhtml";               
        }else{
            Group g1 = new Group("Customers", "Group of Customers");
            user = new User(customer.getUser().getUserName(), customer.getUser().getPassword());
            
            user.addGroup(g1);
            userService.update(user);
            
            customerService.update(customer);
            addressService.update(customer.getAddress());
            
            facesContext.addMessage(null, new FacesMessage("Customer updation is complete !!!, Thank you."));
        }
        return "/customerPortal/welcome.xhtml";
    }
    
    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
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
    
    
}
