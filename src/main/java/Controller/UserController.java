/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.UsrDao;
import Entity.Usr;
import static filter.utils.createJWT;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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

@RequestScoped
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class UserController {
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
           Response.status(Response.Status.BAD_REQUEST).entity("Не указан login и password")
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS")
                    .header("Access-Control-Allow-Headers", "Content-Type")
                    .allow("OPTIONS")
                    .build()
           );            
        }
        try {
            usrDao.create(usr);
            return Response.ok()
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS")
                    .header("Access-Control-Allow-Headers", "Content-Type")
                    .allow("OPTIONS")
                    .entity("Successful registration, " + usr.getLogin() + " !")
                    .build();            
        }
        catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }
    
    @POST
    @Path("/login")
    public Response authenticateUser(Usr usr) {
        try {
            // sended login and password
            String login = usr.getLogin();
            String password = usr.getPassword();
            //List of finded users - i need a first           
            List<Usr> users_find = usrDao.findByLogin(login);
            Usr user = users_find.get(0);
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
    //            Claims claims = decodeJWT(jwt);
    //            System.err.println("claims = " + claims.toString());
                return Response.ok()
                        .header(AUTHORIZATION, "Bearer " + jwt)
//                        .header("Access-Control-Allow-Origin", "*")
//                        .header("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS")
//                        .header("Access-Control-Allow-Headers", "Content-Type")
//                        .allow("OPTIONS")
                        .entity("AUTHORIZATION requested, token: " + jwt)
                        .build();                
            } 
            // password is incorrect
            else {
                return Response.status(UNAUTHORIZED)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS")
                        .header("Access-Control-Allow-Headers", "Content-Type")
                        .allow("OPTIONS")                   
                        .entity("AUTHORIZATION failed")
                        .entity("wrong password for " + usr.getLogin())
                        .build();         
            }             
        } catch (Exception e) {
            System.err.println(e);
            return Response.status(UNAUTHORIZED)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS")
                    .header("Access-Control-Allow-Headers", "Content-Type")
                    .allow("OPTIONS")                     
                    .entity("AUTHORIZATION failed")
                    .entity(usr.getLogin() + " is not found")
                    .build();
        }
    }
}