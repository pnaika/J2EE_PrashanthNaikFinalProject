/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.pnaika.fp.ejb;

import edu.iit.sat.itmd4515.pnaika.fp.domain.Address;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Billing;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Customer;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Employee;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Parking;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Payment;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Vehicle;
import edu.iit.sat.itmd4515.pnaika.fp.domain.security.Group;
import edu.iit.sat.itmd4515.pnaika.fp.domain.security.User;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Prashanth
 */

@Singleton
@Startup
public class DbPopulator {
    
    @EJB
    private EmployeeService empService;
    
    @EJB
    private CustomerService custService;
    
    @EJB
    private UserService userServive;
    
    @EJB
    private GroupService groupServive;
    
    @EJB
    private AddressService addressService;
    
    @EJB
    private VehicleService vehicleService;
    
    @EJB
    private BillingService billingServive;
    
    @EJB
    private PaymentService paymentService;
    
    @EJB
    private ParkingService parkingService;
    
    public DbPopulator() {
    }
    
    @PostConstruct
    public  void seedDatabase() {
    
        //THIS METHOD WILL PERFORM CRUD TO FATABASE
        
        Group customer = new Group("Customers", "Group of Customers");
        Group employee = new Group("Employees","Group of Employees");
        
        User prash = new User("prashanth", "password");
        User kiran = new User("kiran", "password");
        User kesav = new User("kesav", "password");
        User mouni = new User("mouni", "password");
        User sanjeev = new User("sanjeev", "password");
        User shashank = new User("shashank", "password");
        User ashvik = new User("ashvik", "password");
        User shreyas = new User("shreyas", "password");
        
        prash.addGroup(customer);
        kiran.addGroup(customer);
        mouni.addGroup(customer);
        shreyas.addGroup(customer);
        kesav.addGroup(employee);
        sanjeev.addGroup(employee);
        shashank.addGroup(employee);
        ashvik.addGroup(employee);
        
        Address a1 = new Address("C", "Chicago", "IL", "60616");
        Address a2 = new Address("C", "Bangalore", "Karnataka", "560098");
        Address a3 = new Address("C", "Texas", "TX", "60611");
        Address a4 = new Address("E", "Springfield", "IL", "70616");
        Address a5 = new Address("E", "Shimoga", "Karnataka", "60616");
        Address a6 = new Address("E", "Mumbai", "Maharastra", "12345");
        Address a7 = new Address("C", "Shimoga", "Karnataka", "60616");
        Address a8 = new Address("E", "Mumbai", "Maharastra", "12345");
        
        Customer c1 = new Customer("Prashanth", "3126478554", "pnaika@hawk.iit.edu");
        c1.setUser(prash);
        c1.setAddress(a1);
        
        Customer c2 = new Customer("Kiran", "3126478554", "kiran@hawk.iit.edu");
        c2.setUser(kiran);
        c2.setAddress(a2);
        
        Customer c3 = new Customer("Mouni", "3126478554", "mouni@hawk.iit.edu");
        c3.setUser(mouni);
        c3.setAddress(a3);
        
        Customer c4 = new Customer("Shreyas", "3123654554", "shreyas@hawk.iit.edu");
        c4.setUser(shreyas);
        c4.setAddress(a7);
        
        Employee e1 = new Employee("Kesav", "3698521478", "kesav@iit.edu");
        e1.setUser(kesav);
        e1.setAddress(a4);
        
        Employee e2 = new Employee("Sanjeev", "3698521478", "sanjeev@iit.edu");
        e2.setUser(sanjeev);
        e2.setAddress(a5);
        
        Employee e3 = new Employee("Shashank", "3698521478", "shashank@iit.edu");
        e3.setUser(shashank);
        e3.setAddress(a6);
        
        Employee e4 = new Employee("Ashvik", "30000000000", "ashvik@iit.edu");
        e4.setUser(ashvik);
        e4.setAddress(a8);

        Vehicle v1 = new Vehicle("Four Wheeler");
        Vehicle v2 = new Vehicle("Two Wheeler");
        Vehicle v3 = new Vehicle("Heavy Vehicle");
        
        Payment p1 = new Payment("Credit Card");
        Payment p2 = new Payment("Debit Card");
        Payment p3 = new Payment("Cash");
        Payment p4 = new Payment("Cheque");
        
        
        Parking pr1 = new Parking(2, 20, "P1");
        pr1.setVehicle(v1);
        pr1.setCustomer(c1);
        
        Parking pr2 = new Parking(3, 30, "P2");
        pr2.setVehicle(v3);
        pr2.setCustomer(c2);
        
        Parking pr3 = new Parking(4, 40, "P3");
        pr3.setVehicle(v2);
        pr3.setCustomer(c3);
        
        Parking pr4 = new Parking(1, 10, "P4");
        pr4.setVehicle(v3);
        pr4.setCustomer(c4);
        
        
        Billing b1 = new Billing();
        b1.setCustomer(c1);
        b1.setParking(pr1);
        b1.setPayment(p1);
        b1.setVehicle(v1);
        
        c1.getBilling().add(b1);
        
        Billing b2 = new Billing();
        b2.setCustomer(c2);
        b2.setParking(pr2);
        b2.setPayment(p2);
        b2.setVehicle(v2);
        
        c2.getBilling().add(b2);
        
        Billing b3 = new Billing();
        b3.setCustomer(c3);
        b3.setParking(pr3);
        b3.setPayment(p3);
        b3.setVehicle(v3);
        
        c3.getBilling().add(b3);
        
        c1.getParking().add(pr1);
        c2.getParking().add(pr2);
        c3.getParking().add(pr3);
        c4.getParking().add(pr4);
        
        //TO CREATE TEST DATA IN DATABASE
        groupServive.create(customer);
        groupServive.create(employee);
        
        userServive.create(prash);
        userServive.create(kiran);
        userServive.create(kesav);
        userServive.create(mouni);
        userServive.create(sanjeev);
        userServive.create(shashank);
        userServive.create(ashvik);
        userServive.create(shreyas);
        
        addressService.create(a1);
        addressService.create(a2);
        addressService.create(a3);
        addressService.create(a4);
        addressService.create(a5);
        addressService.create(a6);
        addressService.create(a7);
        addressService.create(a8);
        
        custService.create(c1);
        custService.create(c2);
        custService.create(c3);
        custService.create(c4);
        
        empService.create(e1);
        empService.create(e2);
        empService.create(e3);
        empService.create(e4);
        
        vehicleService.create(v1);
        vehicleService.create(v2);
        vehicleService.create(v3);
        
        paymentService.create(p1);
        paymentService.create(p2);
        paymentService.create(p3);
        paymentService.create(p4);
    
        parkingService.create(pr1);
        parkingService.create(pr2);
        parkingService.create(pr3);
        parkingService.create(pr4);
        
        
        billingServive.create(b1);
        billingServive.create(b2);
        billingServive.create(b3);
        
        
    }
}
