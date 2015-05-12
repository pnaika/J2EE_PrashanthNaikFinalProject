/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.iit.sat.itmd4515.pnaika.fb.bean;

import edu.iit.sat.itmd4515.pnaika.fp.domain.Address;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Billing;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Customer;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Employee;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Parking;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Payment;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Vehicle;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.AddressService;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.BillingService;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.CustomerService;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.EmployeeService;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.ParkingService;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.PaymentService;
import edu.iit.sat.itmd4515.pnaika.fp.ejb.VehicleService;
import edu.iit.sat.itmd4515.pnaika.fp.web.AbstractJSFBean;
import edu.iit.sat.itmd4515.pnaika.fp.web.LoginBean;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

@Named
@RequestScoped
public class EmployeeBean extends AbstractJSFBean{
    
    private static final Logger LOG = Logger.getLogger(EmployeeBean.class.getName());
    
    private Employee employee;
    private Customer customer;
    private Address address;
    private Payment payment;
    protected Parking parking;
    protected Billing billing;
    protected Vehicle vehicle;
    @Inject 
    private LoginBean loginBean;
    
    private String paymentType;
    private String vehicleType;
    
    @EJB
    private EmployeeService employeeservice;
    
    @EJB
    private CustomerService customerService;
    
    @EJB
    private AddressService addressService;
    
    @EJB
    private ParkingService parkingService;
    
    @EJB
    private PaymentService paymentService;
    
    @EJB
    private BillingService billingService;
    
    @EJB
    private VehicleService vehicleService;
    
    private String searchtext = "";

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public EmployeeService getEmployeeservice() {
        return employeeservice;
    }

    public void setEmployeeservice(EmployeeService employeeservice) {
        this.employeeservice = employeeservice;
    }

    public String getSearchtext() {
        return searchtext;
    }

    public void setSearchtext(String searchtext) {
        this.searchtext = searchtext;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
        
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
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

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    
    public Flash getFlash() {
        return flash;
    }

    public void setFlash(Flash flash) {
        this.flash = flash;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public AddressService getAddressService() {
        return addressService;
    }

    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    public ParkingService getParkingService() {
        return parkingService;
    }

    public void setParkingService(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    public BillingService getBillingService() {
        return billingService;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public VehicleService getVehicleService() {
        return vehicleService;
    }

    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
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

    public PaymentService getPaymentService() {
        return paymentService;
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    
    
    
    public EmployeeBean() {
        super.postContructor();
    }
    
    public String Search() {
        return "/employeePortal/custRecords.xhtml";
    }
    
    public List<Customer> getCustomers() {
        try {
            List<Customer> st = null;
            if (this.searchtext.length() == 0) {
                st = customerService.findAll();
            } else {
                st = customerService.findAll();
                customer = customerService.Find(Long.parseLong(this.searchtext));
                if (customer != null) {
                    st.clear();
                    st.add(customer);
                } else {
                    st.clear();
                }
            }
            return st;
        } catch (Exception ex) {
            facesContext.addMessage(null, new FacesMessage("Some Error has occured. Please try later"));
            LOG.log(Level.WARNING, ex.getMessage());
            return null;
        }
    }
    
    public String createPayment() throws ParseException{

        Payment pay = new Payment(paymentType);
        
        paymentService.create(pay);
        facesContext.addMessage(null, new FacesMessage(pay.getPayment_type().toUpperCase()+" Payment Type is added, Thank You!"));
        return "payInfo.xhtml";
    }
    
    public String createVehicle() throws ParseException{
        Vehicle v = new Vehicle(vehicleType);
        
        vehicleService.create(v);
        facesContext.addMessage(null, new FacesMessage(v.getVehicle_type().toUpperCase()+" Vehicle Type is added, Thank You!"));
        return "vehTypes.xhtml";
    }
    
    public String selectCustomer(Customer customer) {
        try {
            this.customer = customer;
            System.out.println("details" +customer.toString());
            return "changeCustomer.xhtml";
        } catch (Exception ex) {
            facesContext.addMessage(null, new FacesMessage("Some Error has occured. Please try later"));

            LOG.log(Level.WARNING, ex.getMessage());
            return loginBean.getPortalPathByRole("/changeCustomer.xhtml");
        }
    }
    
    public void deleteCustomer(Customer st) {
        try {
            this.customer = st;
            billingService.deleteCustomer(st.getCust_id());            
            parkingService.deleteCustomer(st.getCust_id());
            
            Customer c = customerService.findById(st.getCust_id());
            
            customerService.delete(st);
            addressService.deleteById(c.getAddress().getAddress_id());
            
            facesContext.addMessage(null, new FacesMessage("Customer Details was deleted successfully"));
        } catch (Exception ex) {
            facesContext.addMessage(null, new FacesMessage("Some Error has occured. Please try later"));
            LOG.log(Level.WARNING, ex.getMessage());

        }
    }

    public String saveCustomer(){
        customerService.update(customer);
        facesContext.addMessage(null, new FacesMessage("Customer updation is complete !!!, Thank you."));
        return loginBean.getPortalPathByRole("/custRecords.xhtml");
    }
    
    @PostConstruct
    private void postConstruct() {
        super.postContructor();
        employee= employeeservice.findByUsername(loginBean.getRemoteUser());
        customer = new Customer();
        customer.setAddress(new Address());
//        customer.setBilling((List<Billing>) new Billing());
//        customer.setParking((List<Parking>) new Parking());
        billing = new Billing();
        parking = new Parking();
        vehicle = new Vehicle();
        payment = new Payment();
//        LOG.info("Inside EmployeeBean.postConstruct() with " + employee.toString());
    }
    
    public String executeUpdate() {
        LOG.info("Inside EmployeeBean.executeUpdate() with " + employee.toString());
        employeeservice.update(employee);
        return loginBean.getPortalPathByRole("/welcome.xhtml");
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
}
