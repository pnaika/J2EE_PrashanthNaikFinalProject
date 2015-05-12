/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.pnaika.fp.ejb;

import edu.iit.sat.itmd4515.pnaika.fp.domain.Parking;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.Query;

/**
 *
 * @author Prashanth
 */

@Named(value = "parkingService")
@Stateless
public class ParkingService extends AbstractService<Parking>{

    public ParkingService() {
        super(Parking.class);
    }

    @Override
    public List<Parking> findAll() {
        return getEntityManager().createNamedQuery("Parking.findAll",Parking.class).getResultList();
    }
    
    public Parking findById(Long parkingId) {
        return getEntityManager().createNamedQuery("Parking.findById",Parking.class).setParameter("parking_id", parkingId).getSingleResult();
    }
    public Parking delByCustId(Long custId) {
        return getEntityManager().createNamedQuery("Parking.delByCustId",Parking.class).setParameter("cust_id", custId).getSingleResult();
    }
        
    public List<Parking> findCustId(Long custId) {
        return getEntityManager().createNamedQuery("Parking.findCustId",Parking.class).setParameter("cust_id", custId).getResultList();
    }
    public int deleteCustomer(long custId) {
        Query query;
        query = getEntityManager().createQuery("Delete from Parking p where p.customer.cust_id = :custId");
        query.setParameter("custId", custId);        
        int deleteStatus = query.executeUpdate();
        return deleteStatus;
    }
}
