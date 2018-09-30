package com.ctl.javadocker.config;

import javax.enterprise.context.ApplicationScoped;

import org.apache.deltaspike.core.api.config.PropertyFileConfig;

@ApplicationScoped
public class MyPropertyFile implements PropertyFileConfig {

	@Override
	public String getPropertyFileName() {
		return "etc/myapp.properties";
	}

	@Override
	public boolean isOptional() {
		return false;
	}

}
