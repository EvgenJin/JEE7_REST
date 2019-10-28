/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Tools.functions.*;
import static filter.utils.createJWT;
import static filter.utils.decodeJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
    
//    @Inject
//    private KeyGenerator keyGenerator;
    
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
    @Path("/login")
    public Response authenticateUser(UserProfile userProfile) {
        try {
            String login = userProfile.getLogin();
            String password = userProfile.getPassword();
            // Authenticate the user using the credentials provided
            authenticate(login, password);
            System.err.println(login);
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
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS")
                    .header("Access-Control-Allow-Headers", "Content-Type")
                    .allow("OPTIONS")                    
                    .build();
        } catch (Exception e) {
            System.err.println(e);
            return Response.status(UNAUTHORIZED)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS")
                    .header("Access-Control-Allow-Headers", "Content-Type")
                    .allow("OPTIONS")                     
                    .build();
        }
    }
    
    private void authenticate(String login, String password) throws Exception {   
//        if ("asd" == null)
//            throw new SecurityException("Invalid user/password");
    }
       
}
