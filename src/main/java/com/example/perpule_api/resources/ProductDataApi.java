package com.example.perpule_api.resources;

import com.example.perpule_api.modals.ProductDataModal;
import com.example.perpule_api.modals.ResponseModal;
import com.example.perpule_api.repositories.ProductRepository;
import com.example.perpule_api.repositories.UserRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("data_api")
public class ProductDataApi {
	private ProductRepository productRepository = new ProductRepository();
	private UserRepository userRepository = new UserRepository();

    @GET
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<ProductDataModal> readAllProductData(@Context HttpHeaders httpheaders) {
        String token = httpheaders.getHeaderString("auth_token");
        return productRepository.getAllProductData();
    }

	@GET
    @Path("check_auth")
	@Produces({MediaType.TEXT_PLAIN})
	public String createProductData(@Context HttpHeaders httpheaders) {
		String token = httpheaders.getHeaderString("auth_token");
		if (userRepository.isAuthOK(token)){
		    return token;
        }else{
		    return null;
        }
	}

    @POST
    @Path("create")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public ResponseModal createProductData( ProductDataModal productDataModal,@Context HttpHeaders httpheaders) {
        ResponseModal responseModal = new ResponseModal();
	    String token = httpheaders.getHeaderString("auth_token");
		if (userRepository.isAuthOK(token)){
			if (productRepository.createProduct(productDataModal)){
				responseModal.setResponse_code("900");
				responseModal.setResponse_details("product data entered");
			}
		}

	    return responseModal;

    }

	@Path("read/{id}")
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ProductDataModal getData(@PathParam("id") String id){
		return productRepository.getProduct(id);
	}

    @Path("delete/{id}")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public ResponseModal deleteData(@PathParam("id") String id){
        ResponseModal responseModal = new ResponseModal();
        if (productRepository.deleteProduct(id)){
            responseModal.setResponse_code("900");
            responseModal.setResponse_details("deleted");
        }
        return responseModal;
    }
	
}
