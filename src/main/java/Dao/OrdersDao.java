/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Orders;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author eshahov
 */
@Stateless
public class OrdersDao {
    
    @PersistenceContext(unitName = "pers")
    EntityManager em;
    
    public List getAll() {
        return em.createNamedQuery("Orders.findAll", Orders.class).getResultList();
    }
    
    public List findByPersonID(Long id) {
        return em.createNamedQuery("Orders.findByPerson").setParameter("person", id).getResultList();
    }

    public Orders findById(Long id) {
        return em.find(Orders.class, id);
    }

    public void update(Orders orders) {
        em.merge(orders);
    }

    public void create(Orders orders) {
        em.persist(orders);
    }

    public void delete(Orders orders) {
        if (!em.contains(orders)) {
            orders = em.merge(orders);
        }

        em.remove(orders);
    }    
    
}
