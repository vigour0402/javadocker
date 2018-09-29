package com.ctl.javadocker.config;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;

import org.apache.deltaspike.core.api.config.Source;
import org.apache.deltaspike.core.spi.config.ConfigSource;
import org.apache.deltaspike.core.util.PropertyFileUtils;

@Source
@ApplicationScoped
public class MyPropertyFileSource implements ConfigSource {

	private static final String FILENAME = "etc/myapp.properties";

	@Override
	public int getOrdinal() {
		return 150;
	}

	private Properties load() {
		File f = new File(FILENAME);

		try {
			return PropertyFileUtils.loadProperties(f.toURI().toURL());
		} catch (MalformedURLException e) {
			throw new RuntimeException("problem (re)loading property file", e);
		}
	}

	@Override
	public Map<String, String> getProperties() {
		Properties p = load();
		Map<String, String> result = new HashMap<String, String>();
		for (String propertyName : p.stringPropertyNames()) {
			result.put(propertyName, p.getProperty(propertyName));
		}

		return result;
	}

	@Override
	public String getPropertyValue(String key) {
		Properties p = load();
		return p.getProperty(key);
	}

	@Override
	public String getConfigName() {
		return FILENAME;
	}

	@Override
	public boolean isScannable() {
		return true;
	}

}
