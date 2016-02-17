package com.litvinov.bank.accounting.service.resourse;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.litvinov.bank.accounting.service.models.User;
import com.litvinov.bank.accounting.service.models.UserService;

@Path("/balance/")
public class Resourse {
	UserService userService = new UserService();

	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_XML)
	public User getUser(@PathParam("username") String username) throws Exception {
		User user = userService.retrieveUser(username);
		return user;
	}

	@PUT
	@Path("/{username}/{balance}")
	@Produces(MediaType.APPLICATION_XML)
	public User getMessage(@PathParam("username") String username, @PathParam("balance") int balance) throws Exception {

		if (userService.checkUser(username)) {
			userService.addBalance(username, balance);
			return userService.retrieveUser(username);
		} else {
			userService.createUser(username, balance);
			return userService.retrieveUser(username);
		}
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_XML)
	public List<User> getUsers() throws Exception {
		return userService.getAllUsers();
	}
	
	@DELETE
	@Path("/delete/{username}")
	@Produces(MediaType.APPLICATION_XML)
	public User deleteUser(@PathParam("username") String username) throws Exception{
		User user = userService.retrieveUser(username);
		userService.deleteUser(user);
		return user;
	}
	

}