package com.example.perpule_api.resources;


import com.example.perpule_api.repositories.UserRepository;
import com.example.perpule_api.modals.UserInputDataModal;
import com.example.perpule_api.modals.UserOutputDataModal;

import java.util.List;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

@Path("user_api")
public class UserApi {
	private UserRepository userRepository = new UserRepository();
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<String> getUsers() 
	{
		return userRepository.getAllUsers();
		
	}
	
	@GET
	@Path("lol")
	@Produces({MediaType.TEXT_PLAIN})
	public String get_data() {
		return "joeydash";
	}
	
	@POST
	@Path("create")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public UserOutputDataModal createUser(UserInputDataModal userInputputDataModal)
	{

		return userRepository.createUser(userInputputDataModal);
	}
	
	@POST
	@Path("check_user")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public UserOutputDataModal checkUser(UserInputDataModal userInputputDataModal) 
	{
		return userRepository.checkUser(userInputputDataModal);
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

}
