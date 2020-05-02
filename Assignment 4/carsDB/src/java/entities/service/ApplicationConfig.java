package entities.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * @author Tsvetoslav Dimov
 * @date 02/05/2020
 * @studentID 20077038
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(entities.service.CarsFacadeREST.class);
        resources.add(entities.service.UsersFacadeREST.class);
    }
    
}
