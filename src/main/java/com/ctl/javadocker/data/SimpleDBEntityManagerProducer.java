package com.ctl.javadocker.data;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.hibernate.tool.schema.Action;
import org.apache.deltaspike.core.api.config.ConfigResolver;
import org.hibernate.FlushMode;
import org.hibernate.dialect.DerbyTenSevenDialect;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.jboss.weld.util.collections.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.hibernate.cfg.AvailableSettings.*;

import java.util.Map;


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
    	
    	Map<String, Object> emProperties = ImmutableMap.<String, Object>builder()
        .put(JPA_JDBC_DRIVER, ConfigResolver.getPropertyValue("db.driver")) 
        .put(JPA_JDBC_URL, ConfigResolver.getPropertyValue("db.url"))
        .put(JPA_JDBC_USER, ConfigResolver.getPropertyValue("db.user"))
        .put(JPA_JDBC_PASSWORD, ConfigResolver.getPropertyValue("db.pass"))
        .put(DIALECT, DerbyTenSevenDialect.class.getCanonicalName())
        .put(HBM2DDL_AUTO, Action.CREATE)
        .put(SHOW_SQL, false)
        .put(FLUSH_MODE, FlushMode.AUTO)
        .put(QUERY_STARTUP_CHECKING, false)
        .put(GENERATE_STATISTICS, false)
        .put(USE_REFLECTION_OPTIMIZER, false)
        .put(USE_SECOND_LEVEL_CACHE, false)
        .put(USE_QUERY_CACHE, false)
        .put(USE_STRUCTURED_CACHE, false)
        .put(STATEMENT_BATCH_SIZE, 20)
        .put(JPA_PERSISTENCE_PROVIDER, HibernatePersistenceProvider.class.getCanonicalName())
        .build();
    	
        return Persistence
                .createEntityManagerFactory("SimpleDB", emProperties)
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
