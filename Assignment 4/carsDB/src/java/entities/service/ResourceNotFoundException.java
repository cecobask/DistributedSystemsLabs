package entities.service;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

/**
 * @author      Tsvetoslav Dimov
 * @date        02/05/2020
 * @studentID   20077038
 * @description This class extends from ClientErrorException and is used to
 *              provide a 404 error to RESTful API requests, when the response
 *              is null or empty.
 */
public class ResourceNotFoundException extends ClientErrorException {

    /**
     * @description Constructs a ResourceNotFoundException object with the 
     *              specified message and a 404 response code.
     * @param message 
     */
    public ResourceNotFoundException(String message) {
        super(message, Response.Status.NOT_FOUND);
    }

}
