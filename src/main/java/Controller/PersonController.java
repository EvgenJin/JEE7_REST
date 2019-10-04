package Controller;

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
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;

@RequestScoped
@Path("person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonController {

    @Inject
    PersonDao personDao;

    // Все записи
    @GET
    @Path("all")
    public Response getAll() {
        List<Person> persons = personDao.getAll();
            if (persons == null) {
                    throw new RuntimeException("Can't load all messages");
            }        
        return Response.ok(persons).build();
    }
    // Один клиент по ИД
    @GET
    @Path("{id}")
    public Response getTodo(@PathParam("id") Long id) {
        Person person = personDao.findById(id);
        return Response.ok(person).build();
    }
    // Поиск по ФИО
    @GET
    @Path("byfio")
    public Response getByFio(@QueryParam("first_name") String first_name, @QueryParam("second_name") String second_name, @QueryParam("third_name") String third_name) {
        if (first_name == null) {
          throw new WebApplicationException(
            Response.status(Response.Status.BAD_REQUEST)
              .entity("Не указано Имя first_name")
              .build()
          );
        }
        List<Person> persons_list = personDao.findByFio(first_name, second_name, third_name);
        return Response.ok(persons_list).build();
    }
    // Поиск по адресу
    @GET
    @Path("byaddress")
    public Response getByAddress(@QueryParam("address") String address) {
        if (address == null) {
          throw new WebApplicationException(
            Response.status(Response.Status.BAD_REQUEST)
              .entity("Не указан адрес (address)")
              .build()
          );
        }
        List<Person> person_list = personDao.findByAdres(address);
        return Response.ok(person_list).build();
    }
    // поиск по инн
    @GET
    @Path("byinn")
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
    // Обновить
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Person person) {
        Person updatePerson = personDao.findById(id);
        updatePerson.setPerson(person);
        personDao.update(updatePerson);
        return Response.ok().build();
    }
    // Добавить
    @POST
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

