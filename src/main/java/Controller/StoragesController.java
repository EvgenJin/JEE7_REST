package Controller;

import Dao.StoragesDao;
import Entity.Storages;
import Tools.functions;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
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
@Path("/storages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StoragesController {
    
    @Inject
    StoragesDao storagesDao;    
    
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
    public Response addStorage(Storages storages) {
        if (storages.getName()== null || storages.getAddress() == null) {
           throw new WebApplicationException(
                Response.status(Response.Status.BAD_REQUEST).entity("Не указан name и address").build()
           );
        }
        try {
            storagesDao.create(storages);
            return Response.ok().entity("Successful registration, " + storages.getName() + " !").build();
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
    public Response getStoragesAll() {
        List<Storages> storages = storagesDao.getAll();
            if (storages == null) {
                    throw new RuntimeException("Ошибка(storages):ничего не найдено");
            }        
        return Response.ok(storages).build();
    }
    // One by id
    @GET
    @Path("{id}")
    public Response getStorageById(@PathParam("id") Long id) throws JsonProcessingException {
        if (id == null) {
          throw new WebApplicationException(
            Response.status(Response.Status.BAD_REQUEST)
              .entity("Не указан id")
              .build()
          );
        }
        Storages storages = storagesDao.findById(id);
        return Response.ok(storages).build();
    }    
}
