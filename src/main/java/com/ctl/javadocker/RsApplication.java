package com.ctl.javadocker;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Application;


@ApplicationScoped
public class RsApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new LinkedHashSet<Class<?>>();
        resources.add(UtsResource.class);
        resources.add(TimeResource.class);
        resources.add(UserResource.class);
        resources.add(GreetingResource.class);
        return resources;
    }

}