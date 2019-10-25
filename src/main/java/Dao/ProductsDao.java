/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Products;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author admin
 */
@Stateless
public class ProductsDao {
    @PersistenceContext(unitName = "pers")
    EntityManager em;
    
    public List getAll() {
        return em.createNamedQuery("Products.findAll", Products.class).getResultList();
    }
    
    public Products findById(Long id) {
        return em.find(Products.class, id);
    }
}
