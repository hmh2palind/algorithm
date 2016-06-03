package com.directv.javaprojectrest.search;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.directv.javaprojectrest.rest.RestServ;

/**
 * @author HungHM5
 *
 */
public class EDMSearch {
	private final static Logger logger = LoggerFactory .getLogger(RestServ.class);
	
	public static String search() {
		logger.info("Begin search method().");
		// TODO Auto-generated method stub
		Object searchResponse = null;
		try {
			String url = null; 
			Properties props = new Properties();
			props.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
			props.put("java.naming.factory.url.pkgs", "org.jboos.naming.org.interfaces");
			props.put("java.naming.provider.url", url);
		
			InitialContext content = new InitialContext(props);
			searchResponse = content.lookup("edmcs/SearchEjb/remote");
			
			if (searchResponse == null){
				// Just in case
				// TODO Auto-generated catch block
				return null;
			}
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			logger.info("");
			e.printStackTrace();
		}
		logger.info("End search method().");
		return (String)searchResponse;
	}
}
