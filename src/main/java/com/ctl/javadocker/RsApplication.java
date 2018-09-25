package com.ctl.javadocker;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class RsApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(GreetingResource.class);
        return classes;
    }

}
