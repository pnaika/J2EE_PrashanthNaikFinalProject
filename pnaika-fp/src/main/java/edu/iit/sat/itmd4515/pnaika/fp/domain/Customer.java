/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.pnaika.fp.domain;

import edu.iit.sat.itmd4515.pnaika.fp.domain.security.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Prashanth
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Customer.findByName",query = "select c from Customer c where c.cust_name = :cust_name"),
    @NamedQuery(name = "Customer.findById",query = "select c from Customer c where c.cust_id = :cust_id"),
    @NamedQuery(name = "Customer.findByAddrId",query = "select c from Customer c where c.address.address_id = :address_id"),
    @NamedQuery(name = "Customer.findAll",query = "select c from Customer c"),
    @NamedQuery(name = "Customer.findByUsername",query = "select c from Customer c where c.user.userName = :username"),
    @NamedQuery(name = "Customer.findByCustAddrId",query = "select c from Customer c where c.cust_id = :cust_id"),
})
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long cust_id;

//    @OneToOne(cascade = CascadeType.PERSIST)
    @OneToOne
    @JoinColumn(name="Address_ID")
    private Address address;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE)
    private List<Billing> billing = new ArrayList<>();

    @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE)
    private List<Parking> parking = new ArrayList<>();
    
    @OneToOne
    private User user;

    
    private String cust_name;    
    private String cust_phone;    
    private String cust_email;

    public Customer() {
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date CustLastUpdate;

    public Date getCustLastUpdate() {
        return CustLastUpdate;
    }
    
    @PreUpdate
    @PrePersist
    private void setCustLastUpdate(){
        CustLastUpdate = GregorianCalendar.getInstance().getTime();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public List<Billing> getBilling() {
        return billing;
    }

    public void setBilling(List<Billing> billing) {
        this.billing = billing;
    }

    public String getCust_email() {
        return cust_email;
    }
    
    
    public void setCust_email(String cust_email) {
        this.cust_email = cust_email;
    }

    public String getCust_phone() {
        return cust_phone;
    }

    public void setCust_phone(String cust_phone) {
        this.cust_phone = cust_phone;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public Long getCust_id() {
        return cust_id;
    }

    public void setCust_id(Long cust_id) {
        this.cust_id = cust_id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Parking> getParking() {
        return parking;
    }

    public void setParking(List<Parking> parking) {
        this.parking = parking;
    } 

    public void setCustLastUpdate(Date CustLastUpdate) {
        this.CustLastUpdate = CustLastUpdate;
    }

    public Customer(Long cust_id, Address address, User user, String cust_name, String cust_phone, String cust_email, Date CustLastUpdate) {
        this.cust_id = cust_id;
        this.address = address;
        this.user = user;
        this.cust_name = cust_name;
        this.cust_phone = cust_phone;
        this.cust_email = cust_email;
        this.CustLastUpdate = CustLastUpdate;
    }

    public Customer(String cust_name, String cust_phone, String cust_email) {
        this.cust_name = cust_name;
        this.cust_phone = cust_phone;
        this.cust_email = cust_email;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n");
        sb.append("------------------------------------------------------");
        sb.append("\n");
        sb.append("Customer Id  :").append(cust_id).append("\n");
        sb.append("Customer Name  :" ).append(cust_name).append("\n");
        sb.append("Customer email :").append(cust_email).append("\n");        
        sb.append("------------------------------------------------------"+"\n");
        return sb.toString();
    }
}
