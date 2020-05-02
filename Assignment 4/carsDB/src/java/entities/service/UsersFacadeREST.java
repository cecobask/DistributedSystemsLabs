package entities.service;

import entities.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author      Tsvetoslav Dimov
 * @date        02/05/2020
 * @studentID   20077038
 * @description A wrapper class that extends from the AbstractFacade, used to
 *              implement the application logic for each CRUD operation.
 */
@Stateless
@Path("entities.users")
public class UsersFacadeREST extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "carsDBPU")
    private EntityManager em;

    // Default constructor for the UsersFacadeREST class.
    public UsersFacadeREST() {
        super(Users.class);
    }

    /**
     * @description Make the User instance managed and persistent.
     * @param entity 
     */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Users entity) {
        super.create(entity);
    }

    /**
     * @description Merge the state of the User instance into the current persistence context.
     * @param id
     * @param entity 
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Users entity) {
        super.edit(entity);
    }

    /**
     * @description Remove the User instance.
     * @param id 
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

     /**
     * @description Search for User instances with matching primary key (id).
     * @param id
     * @return Instance of a User object or 404 error.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Users find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    /**
     * @description Find all User instances.
     * @return A list of all User object instances or 404 error.
     */
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findAll() {
        return super.findAll();
    }

    /**
     * @description Finds all User instances in a given id range.
     * @param from
     * @param to
     * @return A list of all User object instances or 404 error.
     */
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    /**
     * @description Finds all User instances and returns their count.
     * @return Count of User object instances.
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    /**
     * @description Search for a User entity instance with a matching User ID and Password attributes. 
     * @param userID
     * @param password
     * @return Instance of a matching User object or 404 error.
     */
    @GET
    @Path("fetchUserDetails/{userID}/{password}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Users fetchUserDetails(@PathParam("userID") int userID, @PathParam("password") String password) {
        return super.fetchUserDetails(userID, password);
    }

    /**
     * @description Interface used to interact with the persistence context.
     * @return EntityManager instance
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
