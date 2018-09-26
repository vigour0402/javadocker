package com.ctl.javadocker;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.impl.DefaultCamelContext;
/**
 * Customize Camel Context
 * 
 * @author jcdwyer
 *
 */
@ApplicationScoped
public class CamelConf extends DefaultCamelContext {
 
    @PostConstruct
    void customize() {

    }
 
    @PreDestroy
    void cleanUp() {
        // ...
    }
}