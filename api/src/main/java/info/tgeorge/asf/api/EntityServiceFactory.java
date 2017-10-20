/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tgeorge.asf.api;

/**
 *
 * @author george
 */
public class EntityServiceFactory {

    public EntityServiceFactory() {

    }

    public EntityService createEntityService() {
        return new EntityServiceImpl();
    }

}
