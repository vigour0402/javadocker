package com.ctl.javadocker.config;

import java.io.File;
import java.net.MalformedURLException;


import org.apache.deltaspike.core.impl.config.PropertiesConfigSource;
import org.apache.deltaspike.core.util.PropertyFileUtils;

public class MyPropertyFile extends PropertiesConfigSource {

	private static final String FERT = "etc/myapp.properties";
	private String fullFilename;

	public MyPropertyFile() throws MalformedURLException {
		super(PropertyFileUtils.loadProperties(new File(FERT).toURI().toURL()));
		fullFilename = new File(FERT).toURI().toURL().toExternalForm();
	}
	

	@Override
	public String getConfigName() {
		return fullFilename;
	}


}
