package com.ctl.javadocker;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import org.apache.deltaspike.core.api.config.ConfigProperty;
import org.apache.deltaspike.core.api.config.ConfigResolver;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.camel.component.amqp.AMQPComponent;
import org.apache.qpid.client.PooledConnectionFactory;
import org.apache.qpid.url.URLSyntaxException;

/**
 * 
 * Prepare application for Camel CDI
 * 
 * @author jcdwyer
 *
 */
@ApplicationScoped
public class CdiComp {
	
	@Inject
	@ConfigProperty(name="jd.qpid.url")
	private String amqpUrl;

	

	@Produces
	@Named("cfx")
	public AMQPComponent cfx() throws URLSyntaxException {
//		String url = ConfigResolver.resolve("jd.qpid.url").getValue();
		
		PooledConnectionFactory cx = new PooledConnectionFactory();
		cx.setConnectionURLString(amqpUrl);
		
		return new AMQPComponent(cx);		
	}
	
}