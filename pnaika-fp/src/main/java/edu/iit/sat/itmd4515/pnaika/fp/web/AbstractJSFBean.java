/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.pnaika.fp.web;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 *
 * @author Prashanth
 */
public class AbstractJSFBean {
    
    protected FacesContext facesContext;
    protected Flash flash;
    
    public AbstractJSFBean() {
    }

    @PostConstruct
    protected void postContructor() {
        facesContext = FacesContext.getCurrentInstance();
        flash = facesContext.getExternalContext().getFlash();
    }
    
}
