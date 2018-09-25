package com.ctl.javadocker;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("greeting")
public class GreetingResource {

   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Greeting greeting()  {
       return new Greeting(1, "hello!");
   }
}
