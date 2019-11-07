package Controller;

import Dao.TransfersDao;
import Entity.Transfers;
import Tools.functions;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.math.BigDecimal;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/transfers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransfersController {
    
    @Inject
    TransfersDao transfersDao;
    
    @OPTIONS
    @Path("{param}/{param}")
    public Response options() {
        return Response.ok("")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .build();
    }

    @POST
    @Path("/add")
    public Response addTransfer(Transfers transfers) {
        if (transfers.getCount() == null || transfers.getProduct_id()== null) {
           throw new WebApplicationException(
                Response.status(Response.Status.BAD_REQUEST).entity("Не указано count и product_id").build()
           );
        }
        try {
            transfersDao.create(transfers);
            return Response.ok().entity("Successful!").build();
        }
        catch (Exception e) {
                Throwable ex = functions.getRootCause(e);
                throw new WebApplicationException(
                     Response.status(Response.Status.BAD_REQUEST).entity(ex.getLocalizedMessage()).build()
                );
        }
        
    }
    // Full querry
    @GET
    @Path("all")
    public Response getTransfersAll() {
        List<Transfers> transfers = transfersDao.getAll();
            if (transfers == null) {
                    throw new RuntimeException("Ошибка(transfers):ничего не найдено");
            }        
        return Response.ok(transfers).build();
    }
    // One by id
    @GET
    @Path("{id}")
    public Response getTransferById(@PathParam("id") Long id) throws JsonProcessingException {
        if (id == null) {
          throw new WebApplicationException(
            Response.status(Response.Status.BAD_REQUEST)
              .entity("Не указан id")
              .build()
          );
        }
        Transfers transfers = transfersDao.findById(id);
        return Response.ok(transfers).build();
    }     
    
    @GET
    @Path("test")
    public Response test() {
        BigDecimal transfers = transfersDao.test();
        return Response.ok(transfers).build();        
    }
    
    @GET
    @Path("testlist")
    public Response testlist () {
     List list = transfersDao.testlist();
     return Response.ok(list).build();
    }
    
}
