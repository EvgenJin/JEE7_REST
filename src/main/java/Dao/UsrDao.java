/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Usr;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UsrDao {
    @PersistenceContext(unitName = "pers")
    EntityManager em;

    public Usr findById(String login) {
        return em.createNamedQuery("Usr.findByLogin", Usr.class).getSingleResult();
    }
}
