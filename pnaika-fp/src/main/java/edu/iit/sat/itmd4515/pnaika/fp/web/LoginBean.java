/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.pnaika.fp.web;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import static javax.swing.text.StyleConstants.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Prashanth
 */

@Named
@RequestScoped
public class LoginBean extends AbstractJSFBean{

    private static final Logger LOG = Logger.getLogger(LoginBean.class.getName());
    
    @NotNull(message = "Please enter Username!")
    private String username; 
    
    @NotNull(message = "Please enter password!")
    @Size(min = 8, message = "Password must be at least 8 characters in length.")
    private String password;

    public LoginBean() {
    }
    
    @PostConstruct
    private void postConstruct(){
        super.postContructor();
    }
    public boolean isCustomer() {
        return facesContext.getExternalContext().isUserInRole("customer");
    }
    
    public boolean isEmployee() {
        return facesContext.getExternalContext().isUserInRole("employee");
    }
    
    public String getPortalPathByRole(String path) {
        if (isCustomer()) {
            return "/customerPortal" + path;
        } else if (isEmployee()) {
            return "/employeePortal" + path;
        } else {
            return path;
        }
    }   

    public String doLogin(){
        HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        try {
            req.login(username, password);
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, null, ex);
            facesContext.addMessage(null, new FacesMessage("Bad Login", "Detail: You made a bad login!"));
            return "/login.xhtml";
        }
        return getPortalPathByRole("/welcome.xhtml");
    }
    
    public String getRemoteUser(){
        return facesContext.getExternalContext().getRemoteUser();
    }
    
    public String doLogout(){
        HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        try {
            req.logout();
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, null, ex);
            facesContext.addMessage(null, new FacesMessage("Bad Logout", "Detail: You made a bad login!"));
            return "/login.xhtml";
        }
        return "/login.xhtml";
    }
    
    public void checklogedin() throws IOException {
        LOG.log(Level.INFO, "checklogedin");
        String sUser = getRemoteUser();

        LOG.log(Level.INFO, "checklogedin{0}", sUser);
        if (sUser == null) {
            // return null;
        } else {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + getPortalPathByRole("/welcome.xhtml"));
        }
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
