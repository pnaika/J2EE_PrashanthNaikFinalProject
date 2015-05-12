/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.pnaika.fp.domain;

import edu.iit.sat.itmd4515.pnaika.fp.domain.security.User;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @NamedQuery(name = "Employee.findByName",query = "select e from Employee e where e.emp_name = :emp_name"),
    @NamedQuery(name = "Employee.findById",query = "select e from Employee e where e.emp_id = :emp_id"),
    @NamedQuery(name = "Employee.findAll",query = "select e from Employee e"),
    @NamedQuery(name = "Employee.findByUsername",query = "select e from Employee e where e.user.userName = :username"),
})
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long emp_id;
    
    @OneToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne
    @JoinColumn(name="ADDRESS_ID")
    private Address address;
    
    private String emp_name;    
    private String emp_phone;    
    private String emp_email;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date EmpLastUpdated;

    public Date getEmpLastUpdated() {
        return EmpLastUpdated;
    }

    @PreUpdate
    @PrePersist   
    public void setEmpLastUpdated() {
        EmpLastUpdated = GregorianCalendar.getInstance().getTime();
    }


    public Employee() {
    }

    
    public String getEmp_email() {
        return emp_email;
    }

    public void setEmp_email(String emp_email) {
        this.emp_email = emp_email;
    }

    public String getEmp_phone() {
        return emp_phone;
    }

    public void setEmp_phone(String emp_phone) {
        this.emp_phone = emp_phone;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public Long getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Long emp_id) {
        this.emp_id = emp_id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setEmpLastUpdated(Date EmpLastUpdated) {
        this.EmpLastUpdated = EmpLastUpdated;
    }

    public Employee(String emp_name, String emp_phone, String emp_email) {
        this.emp_name = emp_name;
        this.emp_phone = emp_phone;
        this.emp_email = emp_email;
    }
        
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n");
        sb.append("------------------------------------------------------");
        sb.append("\n");
        sb.append("Employee Id :").append(emp_id).append("\n");
        sb.append("Employee Name :").append(emp_name).append("\n");
        sb.append("Employee email :").append(emp_email).append("\n");
        sb.append("------------------------------------------------------"+"\n");
        return sb.toString();
    }
}
