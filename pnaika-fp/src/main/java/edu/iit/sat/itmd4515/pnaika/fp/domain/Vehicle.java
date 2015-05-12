/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.pnaika.fp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Prashanth
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Vehicle.findById",query = "select v from Vehicle v where v.vehicle_id = :vehicle_id"),
    @NamedQuery(name = "Vehicle.findAll",query = "select v from Vehicle v"),
    @NamedQuery(name = "Vehicle.findByType",query = "select v from Vehicle v where v.vehicle_type = :vehicle_type"),
    @NamedQuery(name = "Vehicle.findType",query = "select v.vehicle_type from Vehicle v"),
})

public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long vehicle_id;    
    private String vehicle_type;

    public Vehicle() {
    }

    public Vehicle(Long vehicle_id, String vehicle_type) {
        this.vehicle_id = vehicle_id;
        this.vehicle_type = vehicle_type;
    }

    public Vehicle(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }
    
//    
//    public List<Billing> getBilling() {
//        return billing;
//    }
//
//    public void setBilling(List<Billing> billing) {
//        this.billing = billing;
//    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public Long getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(Long vehicle_id) {
        this.vehicle_id = vehicle_id;
    }   
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n");
        sb.append("------------------------------------------------------");
        sb.append("\n");
        sb.append("Vehicle Id :").append(vehicle_id).append("\n");
        sb.append("Vehicle Type :").append(vehicle_type).append("\n");
        sb.append("------------------------------------------------------"+"\n");
        return sb.toString();
    }

}
