package Dao;

import Entity.Storages;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class StoragesDao {
    @PersistenceContext(unitName = "pers")
    EntityManager em;            

    public List getAll() {
        return em.createNamedQuery("Storages.findAll", Storages.class).getResultList();
    }
    
    public Storages findById(Long id) {
        return em.find(Storages.class, id);
    }    
    
    public void create(Storages storages) {
        try {
            em.persist(storages);  
            em.flush();
        }
        catch (Exception e) {
            System.err.println("!!!->STOP" + e.getLocalizedMessage());
            throw e;
        }        
    } 
    
}
