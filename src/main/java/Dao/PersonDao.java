/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Person;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author admin
 */
@Stateless
public class PersonDao {
    
    @PersistenceContext(unitName = "pers")
    EntityManager em;
    
    public List getAll() {
        return em.createNamedQuery("Person.findAll", Person.class).getResultList();
    }

    public Person findById(Integer id) {
        return em.find(Person.class, id);
    }

    public void update(Person person) {
        em.merge(person);
    }

    public void create(Person person) {
        em.persist(person);
    }

    public void delete(Person person) {
        if (!em.contains(person)) {
            person = em.merge(person);
        }

        em.remove(person);
    }    
    
}
