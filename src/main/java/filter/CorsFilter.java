package filter;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class CorsFilter implements ContainerResponseFilter {
    
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
//        if ("options".equalsIgnoreCase(containerRequestContext.getRequest().getMethod())) {
//          if(Response.Status.Family.familyOf(containerResponseContext.getStatus()) == Response.Status.Family.SUCCESSFUL) {
//            return;
//          }
//          containerResponseContext.setStatus(Response.Status.NO_CONTENT.getStatusCode());
//          containerResponseContext.setEntity("");
//        }    
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
        responseContext.getHeaders().add("Access-Control-Allow-Headers", "Content-Type");
        responseContext.getHeaders().add("Allow", "OPTIONS");
    }
}