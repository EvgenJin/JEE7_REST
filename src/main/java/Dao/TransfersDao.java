package Dao;
import Entity.Transfers;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TransfersDao {
    @PersistenceContext(unitName = "pers")
    EntityManager em;            

    public List getAll() {
        return em.createNamedQuery("Transfers.findAll", Transfers.class).getResultList();
    }
    
    public Transfers test (Long id) {
        return (Transfers) em.createNamedQuery("Transfers.getByStorageProduct").setParameter("product_id", id).getSingleResult();
    }
    
    public Transfers findById(Long id) {
        return em.find(Transfers.class, id);
    }
    
    public void create(Transfers transfers) {
        try {
            em.persist(transfers);
            em.flush();
        }
        catch (Exception e) {
            System.err.println("!!!->STOP" + e.getLocalizedMessage());
            throw e;
        }
    } 
    
}

