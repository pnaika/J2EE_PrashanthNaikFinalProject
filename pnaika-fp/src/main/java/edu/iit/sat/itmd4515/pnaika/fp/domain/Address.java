/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.pnaika.fp.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @NamedQuery(name = "Address.findById",query = "select a from Address a where a.address_id = :address_id"),
    @NamedQuery(name = "Address.findAll",query = "select a from Address a"),
})
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long address_id;
    
    @OneToOne(mappedBy="address",cascade = CascadeType.REMOVE)
    private Employee owner;

    @OneToOne(mappedBy="address",cascade = CascadeType.REMOVE)
    private Customer cust;

    private String address_type;  // tell if this address belong to cutomer or employee
    private String address_city;    
    private String address_state;
    private String address_zip;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date AddressLastUpdate;

    public Date getAddressLastUpdate() {
        return AddressLastUpdate;
    }

    @PreUpdate
    @PrePersist
    public void setAddressLastUpdate() {
        AddressLastUpdate = GregorianCalendar.getInstance().getTime();
    }

    public String getAddress_zip() {
        return address_zip;
    }

    public void setAddress_zip(String address_zip) {
        this.address_zip = address_zip;
    }

    public String getAddress_state() {
        return address_state;
    }

    
    public void setAddress_state(String address_state) {
        this.address_state = address_state;
    }

    public String getAddress_city() {
        return address_city;
    }

    public void setAddress_city(String address_city) {
        this.address_city = address_city;
    }

    public String getAddress_type() {
        return address_type;
    }

    public void setAddress_type(String address_type) {
        this.address_type = address_type;
    }

    public Employee getOwner() {
        return owner;
    }

    public Customer getCust() {
        return cust;
    }

    public Long getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Long address_id) {
        this.address_id = address_id;
    }

    public void setOwner(Employee owner) {
        this.owner = owner;
    }

    public void setCust(Customer cust) {
        this.cust = cust;
    }

    public Address() {
    }
    
    
    public Address(String address_type, String address_city, String address_state, String address_zip) {
        this.address_type = address_type;
        this.address_city = address_city;
        this.address_state = address_state;
        this.address_zip = address_zip;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n");
        sb.append("------------------------------------------------------");
        sb.append("\n");
        sb.append("address_id :").append(address_id).append("\n");
        sb.append("address_type :").append(address_type).append("\n");
        sb.append("address_State :").append(address_state).append("\n");
        sb.append("address_city :").append(address_city).append("\n");
        sb.append("------------------------------------------------------"+"\n");
        return sb.toString();
    }
    
}
