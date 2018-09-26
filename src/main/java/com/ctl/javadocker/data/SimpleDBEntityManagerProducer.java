package com.ctl.javadocker.data;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 
 * Create SimpleDB Entity Manager
 * 
 * @author jcdwyer
 *
 */
@ApplicationScoped
public class SimpleDBEntityManagerProducer {
	private static final Logger LOG = LoggerFactory.getLogger(SimpleDBEntityManagerProducer.class);
	
    @Produces
    @SimpleDB
    private EntityManager  createEM() {
    	LOG.info("Create");
        return Persistence
                .createEntityManagerFactory("SimpleDB")
                .createEntityManager();
    }
	
    public void close(
            @Disposes @SimpleDB  EntityManager entityManager) {
    	LOG.info("close");
        entityManager.close();
    }
    
//    @Produces
//    TransactionManager createTM() {
//    	tm = new HibernateTransactionManager();
//    }
}
