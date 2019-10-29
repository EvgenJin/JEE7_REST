package Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import Dao.OrdersContentDao;
import Dao.OrdersDao;
import Entity.Orders;
import Entity.OrdersContent;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

@RequestScoped
@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrdersController {
   
    @Inject
    OrdersDao ordersDao;  
    
    @Inject
    OrdersContentDao ordersContentDao;
        
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
    // all records
    @GET
    @Path("all")
    public Response getOrdersAll() {
        List<Orders> orders = ordersDao.getAll();
            if (orders == null) {
                    throw new RuntimeException("Ошибка(orders):ничего не найдено");
            }        
        return Response.ok(orders).build();
    }
    
    // One record by ID
    @GET
    @Path("{id}")
    public Response getOrderById(@PathParam("id") Long id) throws JsonProcessingException {
        if (id == null) {
          throw new WebApplicationException(
            Response.status(Response.Status.BAD_REQUEST).entity("Не указан id заказа (id)").build()
          );
        }
        Orders order = ordersDao.findById(id);
        List<OrdersContent> content_list = ordersContentDao.findByOrderID(id);
        order.setOrders_content(content_list);
        return Response.ok(order).build();
    }
    
    // search order by recs
    @POST
    @Path("search")
    public Response findByRecs(Orders order) {
        return Response.ok(ordersDao.findByRecs(
            order.getDescription(),
            order.getAmount(),
            order.getTitle(),
            order.getDatein(),
            order.getDateout(),
            order.getComment(),
            order.getPersonid())
        ).build();
    }
    
    // Update
    @PUT
    @Path("{id}")
    public Response updateOrder(@PathParam("id") Long id, Orders order) {
        Orders updateOrder = ordersDao.findById(id);
        updateOrder.setOrders(order);
        ordersDao.update(updateOrder);
        return Response.ok().build();
    }
    
    // Insert
    @POST
    @Path("add")
    public Response createOrder(Orders order) throws SQLException {
        Date today = new Date(Calendar.getInstance().getTime().getTime());
        order.setDatein(today);
        ordersDao.create(order);
        return Response.ok("ok").build();
    }
    
    // Delete
    @DELETE
    @Path("{id}")
    public Response deleteOrder(@PathParam("id") Long id) {
        Orders getOrder = ordersDao.findById(id);
        ordersDao.delete(getOrder);
        return Response.ok().build();
    }    
    
    // ------------------------------------- orders content -------------------------------------
    // Content by one order
    @GET
    @Path("content/{id}")
    public Response getOrdersContentAll(@PathParam("id") Long id) {
        if (id == null) {
          throw new WebApplicationException(
            Response.status(Response.Status.BAD_REQUEST)
              .entity("Не указан id заказа (id)")
              .build()
          );
        }        
        List<OrdersContent> orders = ordersContentDao.findByOrderID(id);
            if (orders == null) {
                    throw new RuntimeException("Ошибка(orders):ничего не найдено");
            }        
        return Response.ok(orders).build();
    }
}

