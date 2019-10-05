package Controller;

import Dao.OrdersDao;
import Dao.PersonDao;
import Entity.Person;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import Tools.functions;
import java.math.BigInteger;
import java.sql.Date;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;

@RequestScoped
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonController {

    @Inject
    PersonDao personDao;
    
    @Inject
    OrdersDao ordersDao;    

    // Все записи
    @GET
    @Path("person/all")
    public Response getAll() {
        List<Person> persons = personDao.getAll();
            if (persons == null) {
                    throw new RuntimeException("Ошибка:ничего не найдено");
            }        
        return Response.ok(persons).build();
    }
    // Один клиент по ИД
    @GET
    @Path("person/{id}")
    public Response getTodo(@PathParam("id") Long id) {
        return id != 0 ? Response.ok(personDao.findById(id)).build() : Response.ok(personDao.getAll()).build();
    }
    // Поиск по реквизитам имя фамилия отчество инн дата рождения адрес 
    @POST
    @Path("person/search")
    public Response getByReqs (Person person){
        return person.getFname() != null ? 
                  Response.ok(personDao.findByFio(
                    person.getFname(),
                    person.getSname(),
                    person.getTname(),
                    person.getInn(),
                    person.getAddres(),
                    person.getDateOfBirth())
                  ).build() 
                : Response.status(Response.Status.BAD_REQUEST) .entity("Не указано Имя fname").build();
    }
  
    // Обновить
    @PUT
    @Path("person/{id}")
    public Response update(@PathParam("id") Long id, Person person) {
        Person updatePerson = personDao.findById(id);
        updatePerson.setPerson(person);
        personDao.update(updatePerson);
        return Response.ok().build();
    }
    // Добавить
    @POST
    @Path("person")
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
    @Path("person/{id}")
    public Response delete(@PathParam("id") Long id) {
        Person getPerson = personDao.findById(id);
        personDao.delete(getPerson);
        return Response.ok().build();
    }
    
    
    // Один заказ по ИД
    @GET
    @Path("orders/by_id")
    public Response findById(@QueryParam("id") Long id) {
        System.err.println(id);
        return id != null ?
                  Response.ok(ordersDao.findById((id))).build() 
                : Response.status(Response.Status.BAD_REQUEST) .entity("Не указан id заказа (id)").build();
    }
    
    @GET
    @Path("orders/by_person")
    public Response findByPersonId(@QueryParam("person_id") Long id) {
        return id != null ? 
                  Response.ok(ordersDao.findByPersonID(id)).build() 
                : Response.status(Response.Status.BAD_REQUEST) .entity("Не указан id заказа (id)").build();
    }
    
    
//    -------------------------------------------  рудименты ------------------------------------------- 
    // поиск по инн
    @GET
    @Path("person/byinn")
    public Response getByInn(@QueryParam("inn") BigInteger inn) {
        if (inn == null) {
          throw new WebApplicationException(
            Response.status(Response.Status.BAD_REQUEST)
              .entity("Не указан ИНН (inn)")
              .build()
          );
        }
        Person persons = personDao.findByInn(inn);
        return Response.ok(persons).build();
    }    

}

