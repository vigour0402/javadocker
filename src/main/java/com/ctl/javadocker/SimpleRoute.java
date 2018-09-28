package com.ctl.javadocker;

import javax.enterprise.inject.Vetoed;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple Camel Route with Logging and injected cdi beans
 * 
 * @author jcdwyer
 *
 */
@Vetoed
public class SimpleRoute extends RouteBuilder {
	private static final Logger LOG = LoggerFactory.getLogger(SimpleRoute.class);
	//	private static final Logger LOG = LoggerFactory.getLogger(SimpleRoute.class);

	@Override
	public void configure() {
		LOG.info("create Route IN - START");
		from("amqp:queue:test.in")
		    .log(LoggingLevel.INFO, SimpleRoute.class.getName(), "Start Simple Route")
		    .bean("SimpleProcessor")
		    .log(LoggingLevel.INFO, SimpleRoute.class.getName(), "Finish Simple Route")
		    .to("amqp:test.out");
		LOG.info("create Route IN - DONE");
	}

}
