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
import javax.ws.rs.core.MediaType;

@Path("user_api")
public class UserApi {
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<String> getUsers() 
	{
		UserRepository userRepository = new UserRepository();
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
		UserRepository userRepository = new UserRepository();
		return userRepository.createUser(userInputputDataModal);
	}
	
	@POST
	@Path("check_user")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public UserOutputDataModal checkUser(UserInputDataModal userInputputDataModal) 
	{
		UserRepository userRepository = new UserRepository();
		return userRepository.checkUser(userInputputDataModal);
	}

}
