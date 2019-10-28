/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Usr;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UsrDao {
    @PersistenceContext(unitName = "pers")
    EntityManager em;

    public List<Usr> findByLogin(String login) {
        return em.createNamedQuery("Usr.findByLogin").setParameter("login", login).getResultList();
    }
        
    public void create(Usr usr) {
        em.persist(usr);
    }    
}
