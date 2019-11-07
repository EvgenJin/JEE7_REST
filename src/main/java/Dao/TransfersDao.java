package Dao;
import Entity.Transfers;
import static com.sun.javafx.fxml.expression.Expression.add;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class TransfersDao {
    @PersistenceContext(unitName = "pers")
    EntityManager em;            

    public List getAll() {
        return em.createNamedQuery("Transfers.findAll", Transfers.class).getResultList();
    }
    
    public BigDecimal test () {
        Query q = em.createNativeQuery("select sum(count) from transfers where st_to = ?storage_id and product_id = ?product_id "
                                    + "minus "
                                    + "select sum(count) from transfers where st_from = ?storage_id and product_id = ?product_id");
        q.setParameter("storage_id", 1);
        q.setParameter("product_id", 1);
        return (BigDecimal) q.getSingleResult();
    }
    
    public List testlist() throws NoSuchFieldException, IOException {
        List resList = new ArrayList();
        Query q = em.createNativeQuery("select s.address, s.code, s.name, "
                                    + "(select sum(count) from transfers where st_to = s.id and product_id = 1 "
                                    + "minus "
                                    +"select sum(count) from transfers where st_from = s.id and product_id = 1) "
                                    + "from storages s "
        );
        List<Object[]> list = q.getResultList();
        
        for (Object[] num : list) 
        {
            String address = num[0].toString();
//            String code = num[1].toString();
//            String name = num[2].toString();
//            Long coun = (Long) num[3];
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                  .add("address", address);

            JsonObject jsonObject = objectBuilder.build();

            String jsonString;
            try(Writer writer = new StringWriter()) {
                Json.createWriter(writer).write(jsonObject);
                jsonString = writer.toString();
            }
            resList.add(jsonString);
        }        
//        return q.getResultList();
        return resList;
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

