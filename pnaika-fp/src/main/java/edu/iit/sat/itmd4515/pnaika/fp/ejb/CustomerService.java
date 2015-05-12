/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.pnaika.fp.ejb;

import edu.iit.sat.itmd4515.pnaika.fp.domain.Address;
import edu.iit.sat.itmd4515.pnaika.fp.domain.Customer;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author prashanth
 */

@Named(value = "customerService")
@Stateless
public class CustomerService {

    @PersistenceContext(unitName = "pnaikaPU")
    private EntityManager em;

    public CustomerService() {
    }

    public void create(Customer c) {
        em.persist(c);
    }

    public void update(Customer c) {
        em.merge(c);
    }

    public void remove(Customer c) {
        em.remove(c);
    }
    
    public void delete(Customer c) {
        em.remove(em.merge(c));
    }

    public Customer find(long id) {
        return em.find(Customer.class, id);
    }

    public List<Customer> findAll() {
        return em.createNamedQuery("Customer.findAll",
                Customer.class).getResultList();
    }

    public Customer findByUsername(String userName) {
        return em.createNamedQuery("Customer.findByUsername",
                Customer.class)
                .setParameter("username", userName)
                .getSingleResult();
    }
    
    public Customer Find(long cust_Id) {
        return em.find(Customer.class, cust_Id);
    }
    
    public Customer findByAddrId(Long addressId) {
        return em.createNamedQuery("Customer.findByAddrId",
                Customer.class).setParameter("addressId",addressId).getSingleResult();
    }

    public Customer findByCustAddrId(Long custId) {
        return em.createNamedQuery("Customer.findByCustAddrId",
                Customer.class).setParameter("cust_Id",custId).getSingleResult();
    }
    
    public Customer findById(Long custId) {
        return em.createNamedQuery("Customer.findById",Customer.class).setParameter("cust_id", custId).getSingleResult();
    }

}
