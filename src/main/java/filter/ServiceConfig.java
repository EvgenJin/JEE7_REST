package filter;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("service")
public class ServiceConfig extends Application {

    private Set<Object> singletons = new HashSet<>();

    public ServiceConfig() {
        singletons.add(new CorsFilter());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

}