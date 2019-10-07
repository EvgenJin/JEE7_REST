package Dao;

import Entity.OrdersContent;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OrdersContentDao {
    @PersistenceContext(unitName = "pers")
    EntityManager em;

    public List getAll() {
        return em.createNamedQuery("OrdersContent.findAll", OrdersContent.class).getResultList();
    }
    
    public List findByOrderID(Long id) {
        return em.createNamedQuery("OrdersContent.findByOrderid").setParameter("orderid", id).getResultList();
    }

    public OrdersContent findById(Long id) {
        return em.find(OrdersContent.class, id);
    }
    
    public void update(OrdersContent ordersContent) {
        em.merge(ordersContent);
    }

    public void create(OrdersContent ordersContent) {
        em.persist(ordersContent);
    }

    public void delete(OrdersContent ordersContent) {
        if (!em.contains(ordersContent)) {
            ordersContent = em.merge(ordersContent);
        }
        em.remove(ordersContent);
    }    
    
}
