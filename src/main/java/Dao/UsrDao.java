/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Usr;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import org.eclipse.persistence.exceptions.DatabaseException;

@Stateless
public class UsrDao {
    @PersistenceContext(unitName = "pers")
    EntityManager em;

    public Usr findByLogin(String login) {
        try {
            Usr usr = (Usr) em.createNamedQuery("Usr.findByLogin").setParameter("login", login).getSingleResult();
            return usr;
        }
        catch (Exception e) {
            return null;
        }
        
    }
        
    public void create(Usr usr) {
        try {
            System.err.println("!!!->2");
            em.persist(usr);  
            em.flush();
            System.err.println("!!!->3");
        }
        catch (Exception e) {
            System.err.println("!!!->STOP" + e.getLocalizedMessage());
            throw e;
        }        
//        catch (  ConstraintViolationException e) {
//           throw new PersistenceException("Cannot persist invalid entity: " + usr);
//         }
//       catch (  PersistenceException e) {
//           throw new PersistenceException("Error persisting entity: " + usr,e);
//         }
        
    }    
}
