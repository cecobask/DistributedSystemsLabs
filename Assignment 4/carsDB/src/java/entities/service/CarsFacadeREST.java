package entities.service;

import entities.Cars;
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
@Path("entities.cars")
public class CarsFacadeREST extends AbstractFacade<Cars> {

    @PersistenceContext(unitName = "carsDBPU")
    private EntityManager em;

    // Default constructor for the CarsFacadeREST class.
    public CarsFacadeREST() {
        super(Cars.class);
    }

    /**
     * @description Make the Car instance managed and persistent.
     * @param entity 
     */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Cars entity) {
        super.create(entity);
    }

    /**
     * @description Merge the state of the Car instance into the current persistence context.
     * @param id
     * @param entity 
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Cars entity) {
        super.edit(entity);
    }

    /**
     * @description Remove the Car instance.
     * @param id 
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    /**
     * @description Search for Car instances with matching primary key (id).
     * @param id
     * @return Instance of a Car object or 404 error.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Cars find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    /**
     * @description Find all Car instances.
     * @return A list of all Car object instances or 404 error.
     */
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cars> findAll() {
        return super.findAll();
    }

    /**
     * @description Finds all Car instances in a given id range.
     * @param from
     * @param to
     * @return A list of all Car object instances or 404 error.
     */
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cars> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    /**
     * @description Finds all Car instances and returns their count.
     * @return Count of Car object instances.
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    /**
     * @description Search for Car entity instances with matching Model and Year attributes. 
     * @param cModel
     * @param cYear
     * @return A list of all Car object instances or 404 error.
     */
    @GET
    @Path("fetchCarDetailsByYear/{cModel}/{cYear}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cars> fetchCarDetailsByYear(@PathParam("cModel") String cModel, @PathParam("cYear") int cYear) {
        return super.fetchCarDetailsByYear(cModel, cYear);
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
