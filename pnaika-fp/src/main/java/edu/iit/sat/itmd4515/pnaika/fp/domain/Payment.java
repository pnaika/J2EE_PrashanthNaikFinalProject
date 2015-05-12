/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.pnaika.fp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
/**
 *
 * @author Prashanth
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Payment.findById",query = "select p from Payment p where p.payment_id = :payment_id"),
    @NamedQuery(name = "Payment.findAll",query = "select p from Payment p"),
    @NamedQuery(name = "Payment.findByType",query = "select p from Payment p where p.payment_type = :payment_type"),
    @NamedQuery(name = "Payment.findType",query = "select p.payment_type from Payment p"),
})
public class Payment implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long payment_id;    
    
    private String payment_type;

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public Long getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(Long payment_id) {
        this.payment_id = payment_id;
    }   

    public Payment(String payment_type) {
        this.payment_type = payment_type;
    }

    public Payment() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n");
        sb.append("------------------------------------------------------");
        sb.append("\n");
        sb.append("Payment Id :").append(payment_id).append("\n");
        sb.append("Payment Type :").append(payment_type).append("\n");
        sb.append("------------------------------------------------------"+"\n");
        return sb.toString();
    }

}
