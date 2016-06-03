package com.directv.javaprojectrest.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.directv.javaprojectrest.search.EDMSearch;
import com.sun.jersey.api.representation.Form;
import com.xxx.yyy.system.Version;

@Path("/")
public class RestServ {
	private final static Logger logger = LoggerFactory .getLogger(RestServ.class);

	@POST
	@Path("search")
	public Response search(@Context HttpServletRequest req, Form params) {
		logger.info("processing search method. ");
		return edmSearch(req, params);
	}

	private Response edmSearch(HttpServletRequest req, Form params) {
		logger.info("processing edmSeach method. ");
		Response finalResponse = null;
		try {
			finalResponse = new Response() {
				@Override
				public int getStatus() {
					// TODO Auto-generated method stub
					return 0;
				}

				@Override
				public MultivaluedMap<String, Object> getMetadata() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public Object getEntity() {
					// TODO Auto-generated method stub
					return null;
				}
			};
			// TODO Authentication.
		} catch (Exception ex) {
			return finalResponse;
		}

		// SearchResponse searchResponse = EDMSearch.search(searchRequest);
		EDMSearch.search();
		return null;
	}
	
	@GET
	@Path("getversion")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getVersion(
			@DefaultValue("json") @QueryParam("output") String output) {
		Version version = new Version();
		String mime = "xml".equalsIgnoreCase(output) ? MediaType.APPLICATION_XML : MediaType.APPLICATION_JSON;
		
		return Response.ok(version, mime).build();
	} 
}
