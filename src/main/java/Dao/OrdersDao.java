/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Orders;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class OrdersDao {
    
    @PersistenceContext(unitName = "pers")
    EntityManager em;
    
    public List getAll() {
        return em.createNamedQuery("Orders.findAll", Orders.class).getResultList();
    }
    
    public List findByPersonID(Long id) {
        return em.createNamedQuery("Orders.findByPerson").setParameter("personid", id).getResultList();
    }

    public Orders findById(Long id) {
        return em.find(Orders.class, id);
    }
    
    public List<Orders> findByRecs(String description, BigInteger amount, String title, Date datein, Date dateout, String comment, Long personid) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Orders> query = builder.createQuery(Orders.class);
        Root<Orders> order = query.from(Orders.class);
        query.select(order);

        List<Predicate> predicateList = new ArrayList<>();

        Predicate descriptionPredicate, amountPredicate, titlePredicate, dateinPredicate, dateoutPredicate, commentPredicate, personidPredicate;
        
        if ((description != null) && (!(description.isEmpty()))) {
            descriptionPredicate = builder.like(
                builder.upper(order.<String>get("description")), "%"+description.toUpperCase()+"%");
            predicateList.add(descriptionPredicate);
        }
        
        if ((title != null) && (!(title.isEmpty()))) {
            titlePredicate = builder.like(
                builder.upper(order.<String>get("amount")), "%"+title.toUpperCase()+"%");
            predicateList.add(titlePredicate);
        }
        
        if ((comment != null) && (!(comment.isEmpty()))) {
            commentPredicate = builder.like(
                builder.upper(order.<String>get("comment")), "%"+comment.toUpperCase()+"%");
            predicateList.add(commentPredicate);
        }
        if (amount != null) {
            amountPredicate = builder.equal(order.get("amount"), amount);
            predicateList.add(amountPredicate);
        }
        if (datein != null && dateout == null) {
            dateinPredicate = builder.equal(order.get("datein"), datein);
            predicateList.add(dateinPredicate);
        }
        if (dateout != null && datein == null) {
            dateoutPredicate = builder.equal(order.get("dateout"), dateout);
            predicateList.add(dateoutPredicate);
        }
        if (personid != null) {
            personidPredicate = builder.equal(order.get("personid"), personid);
            predicateList.add(personidPredicate);
        }        
        
        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        query.where(predicates);
        return em.createQuery(query).getResultList();
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
