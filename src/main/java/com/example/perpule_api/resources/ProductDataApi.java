package com.example.perpule_api.resources;

import com.example.perpule_api.modals.ProductDataModal;
import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

@Path("data_api")
public class ProductDataApi {
	@GET
	@Produces({MediaType.TEXT_PLAIN})
	public String checkToken(@Context HttpHeaders httpheaders) {
		String token = httpheaders.getHeaderString("auth_token");
		return token;
	}

	@Path("product/{id}")
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ProductDataModal getData(@PathParam("id") String id){
		Logger.getLogger(getClass()).info("Working");
		return new ProductDataModal();
	}


	
}
