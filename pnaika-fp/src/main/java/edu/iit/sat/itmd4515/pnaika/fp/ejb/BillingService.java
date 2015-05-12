/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.pnaika.fp.ejb;

import edu.iit.sat.itmd4515.pnaika.fp.domain.Billing;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.Query;

/**
 *
 * @author Prashanth
 */

@Named(value = "billingService")
@Stateless
public class BillingService extends AbstractService<Billing>{

    public BillingService() {
        super(Billing.class);
    }

    @Override
    public List<Billing> findAll() {
        return getEntityManager().createNamedQuery("Billing.findAll",Billing.class).getResultList();
    }
    
    public Billing findById(Long billingId) {
        return getEntityManager().createNamedQuery("Billing.findById",Billing.class).setParameter("billing_id", billingId).getSingleResult();
    }
    
    public Billing delByCustId(Long custId) {
        return getEntityManager().createNamedQuery("Billing.delByCustId",Billing.class).setParameter("cust_id", custId).getSingleResult();
    }
    
    public List<Billing> findCustId(Long custId) {
        return getEntityManager().createNamedQuery("Billing.findCustId",Billing.class).setParameter("cust_id", custId).getResultList();
    }
//    
//    public Billing findCustId(Long custId) {
//        return getEntityManager().createNamedQuery("Billing.findCustId",Billing.class).setParameter("cust_id", custId).getSingleResult();
//    }
    public int deleteCustomer(long custId) {
        Query query;
        query = getEntityManager().createQuery("Delete from Billing b where b.customer.cust_id = :custId");
        query.setParameter("custId", custId);        
        int deleteStatus = query.executeUpdate();
        return deleteStatus;
    }
}
