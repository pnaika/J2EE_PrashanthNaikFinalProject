/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.pnaika.fp.ejb;

import edu.iit.sat.itmd4515.pnaika.fp.domain.Vehicle;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author Prashanth
 */

@Named(value = "vehicleService")
@Stateless
public class VehicleService extends AbstractService<Vehicle>{

    public VehicleService() {
        super(Vehicle.class);
    }

    @Override
    public List<Vehicle> findAll() {
        return getEntityManager().createNamedQuery("Vehicle.findAll",Vehicle.class).getResultList();
    }
    
    public List<Vehicle> findType() {
        return getEntityManager().createNamedQuery("Vehicle.findType",Vehicle.class).getResultList();
    }
    
    public Vehicle findById(Long vehicleId) {
        return getEntityManager().createNamedQuery("Vehicle.findById",Vehicle.class).setParameter("vehicle_id", vehicleId).getSingleResult();
    }
    
    public Vehicle findByType(String vehicleType) {
        return getEntityManager().createNamedQuery("Vehicle.findByType",Vehicle.class).setParameter("vehicle_type", vehicleType).getSingleResult();
    }

}
