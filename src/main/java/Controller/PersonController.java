package Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import Dao.OrdersDao;
import Dao.PersonDao;
import Entity.Orders;
import Entity.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

@RequestScoped
@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonController {

    @Inject
    PersonDao personDao;
    
    @Inject
    OrdersDao ordersDao;  
    
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
    public Response getPersonAll() {
        List<Person> persons = personDao.getAll();
            if (persons == null) {
                    throw new RuntimeException("Ошибка(persons):ничего не найдено");
            }        
        return Response.ok(persons)
                        .header("Access-Control-Allow-Origin", "*")
			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .build();
    }
    // Один клиент по ИД
    @GET
    @Path("{id}")
    public Response getPersonById(@PathParam("id") Long id) throws JsonProcessingException {
        if (id == null) {
          throw new WebApplicationException(
            Response.status(Response.Status.BAD_REQUEST)
              .entity("Не указан id")
              .build()
          );
        }
        Person person = personDao.findById(id);
        // все заказы 
        List<Orders> orders_list = ordersDao.findByPersonID(id);
        person.setOrders_list(orders_list);
        // передача QR кода        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode childNode1 = mapper.createObjectNode();
        ((ObjectNode) childNode1).put("path", "person");
        ((ObjectNode) childNode1).put("method", "GET");
        ((ObjectNode) childNode1).put("id", person.getId());
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(childNode1);
        ByteArrayOutputStream bout = QRCode.from(jsonString)
            .withSize(350, 350)
            .to(ImageType.JPG)
            .stream();
        byte[] bytes = bout.toByteArray();
        person.setQrcode(bytes);
        
        return Response.ok(person)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .build();
    }
    // Поиск по реквизитам имя фамилия отчество инн дата рождения адрес 
    @POST
    @Path("search")
    public Response getPersonByReqs (Person person){
        if (person.getFname() == null || "".equals(person.getFname())) {
           throw new WebApplicationException( 
           Response.status(Response.Status.BAD_REQUEST).entity("Не указано Имя fname")
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS")
                    .header("Access-Control-Allow-Headers", "Content-Type")
                    .allow("OPTIONS")
                    .build()
           );
        }
        return Response.ok(personDao.findByRecs(
                person.getFname(),
                person.getSname(),
                person.getTname(),
                person.getInn(),
                person.getAddres(),
                person.getDateOfBirth())
        )
        .header("Access-Control-Allow-Origin", "*")
        .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
        .header("Access-Control-Allow-Credentials", "true")
        .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
        .allow("OPTIONS")
        .build();

    }
    // Обновить
    @PUT
    @Path("{id}")
    public Response updatePerson(@PathParam("id") Long id, Person person) {       
        Person updatePerson = personDao.findById(id);
        updatePerson.setPerson(person);
        personDao.update(updatePerson);
        return Response.ok().build();
    }
    // Добавить
    @POST
    @Path("add")
    public Response create(Person person) {
//        try {
            System.err.println(person.getDateOfBirth());
            if (person.getDateOfBirth() == null) {
                return Response.serverError().status(Response.Status.BAD_REQUEST).entity("не указана дата рождения getDateOfBirth в формате yyyy-mm-dd").build();
            }
            if (person.getFullname() == null) {
                return Response.serverError().status(Response.Status.BAD_REQUEST).entity("не указано ФИО").build();
            }       
            if (person.getSex() == null) {
                return Response.serverError().status(Response.Status.BAD_REQUEST).entity("не указан пол").build();
            }
            personDao.create(person);
//        } catch(Exception e) {
//            e.getMessage();
//            System.err.println(e.getMessage());
//            Response.serverError().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
//            throw e;
//        } finally {
            
//        }
        return Response.ok("ok").build();
    }
    // Удалить
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        Person getPerson = personDao.findById(id);
        personDao.delete(getPerson);
        return Response.ok().build();
    }
}

