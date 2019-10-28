package Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import Dao.ProductsDao;
import Entity.Products;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import filter.JWTTokenNeeded;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

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
//    @JWTTokenNeeded
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
    @JWTTokenNeeded
    public Response getProductById(@PathParam("id") Long id) throws JsonProcessingException {
        if (id == null) {
          throw new WebApplicationException(
            Response.status(Response.Status.BAD_REQUEST)
              .entity("Не указан id")
              .build()
          );
        }
        Products product = productsDao.findById(id);
        // передача QR кода
        ObjectMapper mapper = new ObjectMapper();
        JsonNode childNode1 = mapper.createObjectNode();
        ((ObjectNode) childNode1).put("path", "product");
        ((ObjectNode) childNode1).put("method", "GET");
        ((ObjectNode) childNode1).put("id", product.getId());
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(childNode1);
        ByteArrayOutputStream bout = QRCode.from(jsonString)
            .withSize(350, 350)
            .to(ImageType.JPG)
            .stream();
        byte[] bytes = bout.toByteArray();
        product.setQrcode(bytes);
        return Response.ok(product)
//                            .header("Access-Control-Allow-Origin", "*")
//                            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .build();
    }
}

