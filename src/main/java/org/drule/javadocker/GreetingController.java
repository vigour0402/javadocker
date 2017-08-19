package org.drule.javadocker;

import java.util.concurrent.atomic.AtomicLong;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	@Autowired
	private Environment env;
	
	 private static final String template = "Hello, %s!";
   private final AtomicLong counter = new AtomicLong();

   @RequestMapping("/greeting")
   public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
       return new Greeting(counter.incrementAndGet(),
                           env.getProperty("prop1"));
   }
}
