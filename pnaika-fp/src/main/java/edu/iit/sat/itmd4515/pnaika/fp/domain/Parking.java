/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.pnaika.fp.domain;

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
    @NamedQuery(name = "Parking.findById",query = "select pa from Parking pa where pa.parking_id = :parking_id"),
    @NamedQuery(name = "Parking.findAll",query = "select pa from Parking pa"),
    @NamedQuery(name = "Parking.findCustId",query = "select pa from Parking pa where pa.customer.cust_id = :cust_id"),
    @NamedQuery(name = "Parking.delByCustId",query = "delete from Parking pa where pa.customer.cust_id = :cust_id"),
})
public class Parking implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long parking_id;
    
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="Customer_ID")
    private Customer customer;

    @Temporal(TemporalType.TIMESTAMP)
    private Date parking_date;
    
    private int parking_hours;
    
    @OneToOne
    @JoinColumn(name="Vehicle_ID")
    private Vehicle vehicle;

    private int parking_amount;
    private String parking_slotname;
    
    @PreUpdate
    @PrePersist
    private void setParking_date(){
        parking_date = GregorianCalendar.getInstance().getTime();
    }
    public String getParking_slotname() {
        return parking_slotname;
    }

    public void setParking_slotname(String parking_slotname) {
        this.parking_slotname = parking_slotname;
    }

    public int getParking_amount() {
        return parking_amount;
    }

    public void setParking_amount(int parking_amount) {
        this.parking_amount = parking_hours * 10;
    }

    public int getParking_hours() {
        return parking_hours;
    }
    
    public void setParking_hours(int parking_hours) {
        this.parking_hours = parking_hours;
    }
    
    public Date getParking_date() {
        return parking_date;
    }

    public Long getParking_id() {
        return parking_id;
    }

    public void setParking_id(Long parking_id) {
        this.parking_id = parking_id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }  

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setParking_date(Date parking_date) {
        this.parking_date = parking_date;
    }

    public Parking(int parking_hours, int parking_amount, String parking_slotname) {
        this.parking_hours = parking_hours;
        this.parking_amount = parking_hours * 10;
        this.parking_slotname = parking_slotname;
    }

    public Parking() {
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n");
        sb.append("------------------------------------------------------");
        sb.append("\n");
        sb.append("Parking Id :").append(parking_id).append("\n");
        sb.append("Parking Slot Name :").append(parking_slotname).append("\n");
        sb.append("Parking amount :").append(parking_amount).append("\n");
        sb.append("Parking Date :").append(parking_date).append("\n");
        sb.append("------------------------------------------------------"+"\n");
        return sb.toString();
    }

}
