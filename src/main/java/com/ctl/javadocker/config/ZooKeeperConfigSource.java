package com.ctl.javadocker.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import javax.enterprise.context.ApplicationScoped;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.deltaspike.core.api.config.ConfigResolver;
import org.apache.deltaspike.core.api.config.Source;
import org.apache.deltaspike.core.spi.config.ConfigSource;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Source
//@ApplicationScoped
public class ZooKeeperConfigSource implements ConfigSource {

	private static final Logger logger = LoggerFactory.getLogger(ZooKeeperConfigSource.class);

	// Apache Curator framework used to access Zookeeper
	private AtomicReference<CuratorFramework> curatorReference = new AtomicReference<>();

	// Root node of an application's configuration
	private String applicationId;

	// Prefix of ignored properties
	private static final String IGNORED_PREFIX = "com.ctl.configsource.zookeeper";

	// Property the URL of the Zookeeper instance will be read from
	private static final String ZOOKEEPER_URL_KEY = "com.ctl.configsource.zookeeper.url";

	// Property of the Application Id. This will be the root znode for an
	// application's properties
	private static final String APPLICATION_ID_KEY = "com.ctl.configsource.zookeeper.applicationId";

	// Name of this ConfigSource
	private static final String ZOOKEEPER_CONFIG_SOURCE_NAME = "com.ctl.configsource.zookeeper";

	public ZooKeeperConfigSource() {
	}

	@Override
	public int getOrdinal() {
		return 150;
	}

	@Override
	public Map<String, String> getProperties() {

		final Map<String, String> props = new HashMap<>();

		try {
			final List<String> children = getCuratorClient().getChildren().forPath(applicationId);
			for (final String key : children) {
				final String value = new String(getCuratorClient().getData().forPath(applicationId + "/" + key));
				props.put(key, value);
			}
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}

		return props;
	}

	@Override
	public String getPropertyValue(final String key) {

		/*
		 * Explicitly ignore all keys that are prefixed with the prefix used to
		 * configure the Zookeeper connection. Other wise a stack overflow obviously
		 * happens.
		 */
		if (key.startsWith(IGNORED_PREFIX)) {
			return null;
		}
		try {
			final Stat stat = getCuratorClient().checkExists().forPath(applicationId + "/" + key);

			if (stat != null) {
				return new String(getCuratorClient().getData().forPath(applicationId + "/" + key));
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}
		return null;
	}

	private CuratorFramework getCuratorClient() throws ZooKeeperConfigException {

		CuratorFramework cachedClient = curatorReference.get();
		if (cachedClient == null) {

			final String zookeeperUrl = ConfigResolver.getPropertyValue(ZOOKEEPER_URL_KEY);
			final String applicationIdVal = ConfigResolver.getPropertyValue(APPLICATION_ID_KEY);

			

			// Only create the ZK Client if the properties exist.
			if (zookeeperUrl != null && applicationIdVal != null) {

				logger.info("Configuring ZooKeeperConfigSource using url: " + zookeeperUrl + ", applicationId: "
						+ applicationIdVal);
				applicationId = applicationIdVal;
				if (!applicationId.startsWith("/")) {
					applicationId = "/" + applicationId;
				}
				cachedClient = CuratorFrameworkFactory.newClient(zookeeperUrl,
						new ExponentialBackoffRetry(1000, 3));
				curatorReference.compareAndSet(null, cachedClient);
				cachedClient.start();

			} else {
				throw new ZooKeeperConfigException(
						"Please set properties for \"" + ZOOKEEPER_URL_KEY + "\" and \"" + APPLICATION_ID_KEY + "\"");
			}
		}
		return cachedClient;
	}

	@Override
	public String getConfigName() {
		return ZOOKEEPER_CONFIG_SOURCE_NAME;
	}

	@Override
	public boolean isScannable() {
		return true;
	}
}
