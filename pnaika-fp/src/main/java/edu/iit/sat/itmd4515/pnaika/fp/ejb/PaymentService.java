/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.pnaika.fp.ejb;

import edu.iit.sat.itmd4515.pnaika.fp.domain.Payment;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author Prashanth
 */

@Named(value = "paymentService")
@Stateless
public class PaymentService extends AbstractService<Payment>{

    public PaymentService() {
        super(Payment.class);
    }

    @Override
    public List<Payment> findAll() {
        return getEntityManager().createNamedQuery("Payment.findAll",Payment.class).getResultList();
    }
    
    public List<Payment> findType() {
        return getEntityManager().createNamedQuery("Payment.findType",Payment.class).getResultList();
    }

    public Payment findById(Long paymentId) {
        return getEntityManager().createNamedQuery("Payment.findById",Payment.class).setParameter("payment_id", paymentId).getSingleResult();
    }
    
    public Payment findByType(String paymentType) {
        return getEntityManager().createNamedQuery("Payment.findByType",Payment.class).setParameter("payment_type", paymentType).getSingleResult();
    }
    

}
