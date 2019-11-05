package Controller;

import Dao.UsrDao;
import Entity.Usr;
import static Tools.JWTutils.createJWT;
import Tools.functions;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import javax.ws.rs.core.UriInfo;
import org.eclipse.persistence.exceptions.DatabaseException;

@RequestScoped
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class UsrController {
    @Context
    private UriInfo uriInfo;
    
    @Inject
    UsrDao usrDao;
    
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
    @Path("/register")
    public Response registerUser(Usr usr) {
        if (usr.getLogin() == null || usr.getPassword() == null) {
           throw new WebApplicationException(
                Response.status(Response.Status.BAD_REQUEST).entity("Не указан login и password").build()
           );
        }
        try {
            usrDao.create(usr);
            return Response.ok().entity("Successful registration, " + usr.getLogin() + " !").build();
        }
        catch (Exception e) {
                Throwable ex = functions.getRootCause(e);
                throw new WebApplicationException(
                     Response.status(Response.Status.BAD_REQUEST).entity(ex.getLocalizedMessage()).build()
                );
        }
        
    }
    
    @POST
    @Path("/login")
    public Response authenticateUser(Usr usr) throws IOException {
        try {
            // sended login and password
            String login = usr.getLogin();
            String password = usr.getPassword();
            Usr user = usrDao.findByLogin(login);
            // if password correct 
            if (user.getPassword().equals(password)) {
                String jwtIssuer = "JWT Demo";
                String jwtSubject = "Andrew";
                int jwtTimeToLive = 800000;
                String jwt = createJWT(
                        login, // claim = jti
                        jwtIssuer, // claim = iss
                        jwtSubject, // claim = sub
                        jwtTimeToLive // used to calculate expiration (claim = exp)
                );
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                  .add("token", jwt);
                
                JsonObject jsonObject = objectBuilder.build();
         
                String jsonString;
                try(Writer writer = new StringWriter()) {
                    Json.createWriter(writer).write(jsonObject);
                    jsonString = writer.toString();
                }
                
                return Response.ok()
                        .header(AUTHORIZATION, "Bearer " + jwt)
                        .entity(jsonString)
                        .build();
            }
            // password is incorrect
            else {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                  .add("type", "incorrect_password")
                  .add("message", "wrong password for " + usr.getLogin());
                
                JsonObject jsonObject = objectBuilder.build();
         
                String jsonString;
                try(Writer writer = new StringWriter()) {
                    Json.createWriter(writer).write(jsonObject);
                    jsonString = writer.toString();
                }    
                return Response.status(UNAUTHORIZED)
                    .entity(jsonString)
                    .build();
            }
        } catch (Exception e) {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
              .add("type", "not_exists")
              .add("message", usr.getLogin() + " is not found");

            JsonObject jsonObject = objectBuilder.build();

            String jsonString;
            try(Writer writer = new StringWriter()) {
                Json.createWriter(writer).write(jsonObject);
                jsonString = writer.toString();
            }            
            return Response.status(UNAUTHORIZED)
                .entity(jsonString)
                .build();
        }
    }
}
