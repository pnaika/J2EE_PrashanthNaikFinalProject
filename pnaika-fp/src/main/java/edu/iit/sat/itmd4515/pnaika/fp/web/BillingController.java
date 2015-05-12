/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.pnaika.fp.web;

import edu.iit.sat.itmd4515.pnaika.fp.domain.Address;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Billing;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Customer;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Employee;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Parking;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Payment;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Vehicle;
import edu.iit.sat.itmd4515.pnaika.fp.domain.security.Group;
import edu.iit.sat.itmd4515.pnaika.fp.domain.security.User;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.AddressService;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.BillingService;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.CustomerService;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.EmployeeService;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.GroupService;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.ParkingService;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.PaymentService;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.UserService;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.VehicleService;
import java.text.ParseException;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Prashanth
 */

@Named
@RequestScoped
public class BillingController extends AbstractJSFBean{
 
    @EJB
    private ParkingService parkingService;
    
    @EJB
    private VehicleService vehicleService;

    @EJB
    private PaymentService paymentService;

    @EJB
    private BillingService billingService;

    @EJB
    private CustomerService customerService;

    @EJB
    private EmployeeService employeeService;

    @EJB
    private AddressService addressService;
    
    @EJB
    private UserService userService;
    
    @EJB
    private GroupService groupService;

    @Inject 
    private LoginBean loginBean;
    
    private int parking_hours;
    private int parking_amount;
    private String parking_slotname;
    
    private String payment_type;
    
    private String vehicle_type;

    public BillingController() {
        super.postContructor();
    }

    public ParkingService getParkingService() {
        return parkingService;
    }

    public void setParkingService(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    public VehicleService getVehicleService() {
        return vehicleService;
    }

    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public PaymentService getPaymentService() {
        return paymentService;
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public BillingService getBillingService() {
        return billingService;
    }

    public void setBillingService(BillingService billingService) {
        this.billingService = billingService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
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

    public int getParking_hours() {
        return parking_hours;
    }

    public void setParking_hours(int parking_hours) {
        this.parking_hours = parking_hours;
    }

    public int getParking_amount() {
        return parking_amount;
    }

    public void setParking_amount(int parking_amount) {
        this.parking_amount = parking_hours * 10;
    }

    public String getParking_slotname() {
        return parking_slotname;
    }

    public void setParking_slotname(String parking_slotname) {
        this.parking_slotname = parking_slotname;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }
    
    
    public String createNewBill() throws ParseException {
        
        Parking park = new Parking(parking_hours, parking_amount, parking_slotname);
        Vehicle v = vehicleService.findByType(vehicle_type);
        Payment p = paymentService.findByType(payment_type);
        Billing b = new Billing();
        
        String custName = loginBean.getRemoteUser();
        Customer c = customerService.findByUsername(custName);
        
        park.setVehicle(v);
        park.setCustomer(c);
        
        parkingService.create(park);

        c.getBilling().add(b);
        c.getParking().add(park);        

//        b.getVehicleM().add(v);
//        v.getBilling().add(b);

        b.setCustomer(c);
        b.setVehicle(v);
        b.setParking(park);
        b.setPayment(p);
        
        billingService.create(b);

        facesContext.addMessage(null, new FacesMessage("Your Reservation is successful with the Bill ID " + b.getBilling_id() +" and Parking Amount is " +park.getParking_amount()));
        
//        return "/common/customersignup.xhtml";
        return "welcome.xhtml";
    }
}