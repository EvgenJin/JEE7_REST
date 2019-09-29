package Controller;

import Dao.PersonDao;
import Entity.Person;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import Tools.functions;

@RequestScoped
@Path("person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonController {

    @Inject
    PersonDao personDao;

    @GET
    public Response getAll() {
        List<Person> persons = personDao.getAll();
        return Response.ok(persons).build();
    }
//    @GET
//    public List<Person> getAll() {
//            List<Person> persons = personDao.getAll();
//            if (persons == null) {
//                    throw new RuntimeException("Can't load all messages");
//            }
//            return persons;
//    }    

    @GET
    @Path("{id}")
    public Response getTodo(@PathParam("id") Integer id) {
        Person person = personDao.findById(id);
        return Response.ok(person).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Integer id, Person person) {
        Person updatePerson = personDao.findById(id);
//        updatePerson.setPerson(person);
        personDao.update(updatePerson);
        return Response.ok().build();
    }

    @POST
    public Response create(@Valid Person person) {
        if (person.getDateOfBirth() == null) {
            return Response.serverError().status(Response.Status.BAD_REQUEST).entity("не указана дата рождения").build();
        }
        if (person.getFullname() == null) {
            return Response.serverError().status(Response.Status.BAD_REQUEST).entity("не указано ФИО").build();
        }
        if (person.getSex() == null) {
            return Response.serverError().status(Response.Status.BAD_REQUEST).entity("не указан пол").build();
        }
        personDao.create(person);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Integer id) {
        Person getPerson = personDao.findById(id);
        personDao.delete(getPerson);
        return Response.ok().build();
    }

}

