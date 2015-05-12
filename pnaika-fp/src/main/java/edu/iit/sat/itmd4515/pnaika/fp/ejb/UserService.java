/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.pnaika.fp.ejb;

import edu.iit.sat.itmd4515.pnaika.fp.domain.security.User;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Prashanth
 */

@Stateless
public class UserService extends AbstractService<User>{

    public UserService() {
        super(User.class);
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
