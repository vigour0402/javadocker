package com.ctl.javadocker;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.deltaspike.core.api.config.ConfigProperty;

@Path("greeting")
@ApplicationScoped
public class GreetingResource {

	@Inject
	@ConfigProperty(name = "foo", defaultValue = "NOTHING")
	private String fooValue;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Greeting greeting() {
		return new Greeting(1, "hello!" + " > " + fooValue);
	}
}
