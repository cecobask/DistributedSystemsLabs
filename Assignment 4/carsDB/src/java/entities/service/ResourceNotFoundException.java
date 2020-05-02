/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.service;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

/**
 *
 * @author BASK
 */
public class ResourceNotFoundException extends ClientErrorException {

    public ResourceNotFoundException(String message) {
        super(message, Response.Status.NOT_FOUND);
    }

}
