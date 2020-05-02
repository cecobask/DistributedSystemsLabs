package entities.service;

import entities.Cars;
import entities.Users;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * @author      Tsvetoslav Dimov
 * @date        02/05/2020
 * @studentID   20077038
 * @description An abstract class that implements basic CRUD operations on
 *              various object types.
 */
public abstract class AbstractFacade<T> {

    private final Class<T> entityClass;

    /**
     * @description Default constructor for the AbstractFacade class.
     * @param entityClass 
     */
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    // Interface used to interact with the persistence context.
    protected abstract EntityManager getEntityManager();

    /**
     * @description Make the entity managed and persistent.
     * @param entity 
     */
    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    /**
     * @description Merge the state of the given entity into the current persistence context.
     * @param entity 
     */
    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    /**
     * @description Remove the entity instance.
     * @param entity 
     */
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    /**
     * @description Search for instances of the entity class with matching primary key.
     * @param id
     * @return If the entity instance is contained in the persistence context, it is returned from there.
     */
    public T find(Object id) {
        T result = getEntityManager().find(entityClass, id);
        if (result == null) // No results found.
            throw new ResourceNotFoundException("Resource with id " + id + " not found.");
        return result;
    }

    /**
     * @description Find all instances of the entity class.
     * @return If the entity instance is contained in the persistence context, it is returned from there.
     */
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        List<T> results = getEntityManager().createQuery(cq).getResultList();
        if (results.isEmpty()) // No results found.
            throw new ResourceNotFoundException("No resources found.");
        return results;
    }

    /**
     * @description Finds all instances of the entity class in a given range.
     * @param range 
     * @return If the entity instance is contained in the persistence context, it is returned from there.
     */
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    /**
     * @description Finds all instances of the entity class and returns their count.
     * @return If the entity instance is contained in the persistence context, it is returned from there.
     */
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    /**
     * @description Search for a User entity with matching userID and password.
     * @param userID
     * @param password
     * @return If the entity instance is contained in the persistence context, it is returned from there.
     */
    public Users fetchUserDetails(int userID, String password) {
        Users user = (Users) getEntityManager()
                .createNamedQuery("Users.fetchUserDetails")
                .setParameter("userID", userID)
                .setParameter("password", password)
                .getSingleResult();
        if (user == null) // No results found.
            throw new ResourceNotFoundException("User with id " + userID + " not found.");
        return user;
    }

    /**
     * @description Search for Car entity instances with matching Model and Year attributes. 
     * @param cModel
     * @param cYear
     * @return If the entity instance is contained in the persistence context, it is returned from there.
     */
    public List<Cars> fetchCarDetailsByYear(String cModel, int cYear) {
        List<Cars> cars = (List<Cars>) getEntityManager()
                .createNamedQuery("Cars.fetchCarDetailsByYear", Cars.class)
                .setParameter("cModel", cModel)
                .setParameter("cYear", cYear)
                .getResultList();
        if (cars.isEmpty()) // No results found.
            throw new ResourceNotFoundException("No cars found.");
        return cars;
    }

}
