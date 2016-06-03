package com.xxx.yyy.system;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class XXXConfiguration {

	public static Map<String, Boolean> requredDlsSections = new HashMap<String, Boolean>();
	private Properties properties;
	private static XXXConfiguration configuration = null;

	private XXXConfiguration() {
		properties = new Properties();
	}

	public static synchronized XXXConfiguration getInstance() {
		if (configuration == null) {
			configuration = new XXXConfiguration();
		}

		return configuration;
	}

	/**
	 * Returns the value of the <code>name</code> property, or null if no such
	 * property exists.
	 */
	public String get(String name) {
		return properties.getProperty(name);
	}

	/**
	 * Returns the value of the <code>name</code> property. If no such property
	 * exists, then <code>defaultValue</code> is returned.
	 */
	public String get(String name, String defaultValue) {
		return properties.getProperty(name, defaultValue);
	}

	public boolean readConfigFile(String configPath) {
		// Verifying configuration file
		File configFile = new File(configPath);
		if (!configFile.exists() || !configFile.canRead()) {
			return false;
		}

		try {
			//TODO Configuration file has format XML
			readXXXProperties();
			readYYY();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// TODO Configuration file has format property
		return true;
	}

	private void readYYY() {
		// TODO Auto-generated method stub

	}

	private void readXXXProperties() {
		// TODO Auto-generated method stub

	}

	public boolean loadFullFile() {
		// TODO Auto-generated method stub
		return true;
	}
}
