package Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import Dao.ProductsDao;
import Entity.Products;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {
    @Inject
    ProductsDao productsDao;
    
    @OPTIONS
    @Path("{param}")
    public Response options() {
        return Response.ok("")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .build();
    }    

    // Все записи
    @GET
    @Path("all")
    public Response getProductsAll() {
        List<Products> products = productsDao.getAll();
            if (products == null) {
                    throw new RuntimeException("Ошибка(orders):ничего не найдено");
            }        
        return Response.ok(products).build();
    }
    
    // Один продукт по ИД
    @GET
    @Path("{id}")
    public Response getProductById(@PathParam("id") Long id) throws JsonProcessingException {
        if (id == null) {
          throw new WebApplicationException(
            Response.status(Response.Status.BAD_REQUEST)
              .entity("Не указан id")
              .build()
          );
        }
        Products product = productsDao.findById(id);
        return Response.ok(product)
                            .header("Access-Control-Allow-Origin", "*")
                            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")                
                .build();
    }    
}

