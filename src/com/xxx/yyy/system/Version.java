package com.xxx.yyy.system;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Version {
	private final static Logger LOGGER = LoggerFactory .getLogger(Version.class);
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	static private String version = null;

	static {
		InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("version.properties");
		Properties props = new Properties();
		try {
			props.load(stream);
			version = props.getProperty("typeahead");
		} catch (IOException e) {
			LOGGER.error("Load version error: {}", e);
		}
	}

	public String getVersion() {
		return version;
	}
}
