package Dao;
import Entity.Transfers;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.config.ResultType;

@Stateless
public class TransfersDao {
    @PersistenceContext(unitName = "pers")
    EntityManager em;            

    public List getAll() {
        return em.createNamedQuery("Transfers.findAll", Transfers.class).getResultList();
    }
  
    public List<Map<String, Object>> GetRestByProduct(Long id) throws IOException {
        List<Map<String,Object>> list = null;
        Query q = em.createNativeQuery("select s.address, s.code, s.name, "
                                    + "nvl((select sum(count) from transfers where st_to = s.id and product_id = ?product_id "
                                    + "minus "
                                    +"select sum(count) from transfers where st_from = s.id and product_id = ?product_id),0) ost "
                                    + "from storages s "
        );
        q.setParameter("product_id", id);
        q.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
        list = q.getResultList();
        return list;
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

