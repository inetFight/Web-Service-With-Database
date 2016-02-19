package com.litvinov.bank.accounting.service.resourse;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.litvinov.bank.accounting.service.models.User;
import com.litvinov.bank.accounting.service.models.UserInterface;
import com.litvinov.bank.accounting.service.models.UserService;

@Path("/balance/")
public class Resourse {
	UserInterface userService = new UserService();
	ApplicationContext appContext = new ClassPathXmlApplicationContext("Spring.xml");

	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_XML)
	public User getUser(@PathParam("username") String username) throws Exception {
		UserInterface user =  (UserInterface) appContext.getBean("UserInterface");
		return user.getUser(username);
		
	}

	@PUT
	@Path("/{username}/{balance}")
	@Produces(MediaType.APPLICATION_XML)
	public User getMessage(@PathParam("username") String username, @PathParam("balance") int balance) throws Exception {
		UserInterface user =  (UserInterface) appContext.getBean("UserInterface");
		if (user.checkUser(username)) {
			user.addBalance(username, balance);;
			return user.getUser(username);
		} else {
			 user.createUser(username, balance);
			return user.getUser(username);
		}
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_XML)
	public List<User> getUsers() throws Exception {
		UserInterface user =  (UserInterface) appContext.getBean("UserInterface");
		return user.getAllUsers();
	}
	
	@DELETE
	@Path("/delete/{username}")
	@Produces(MediaType.APPLICATION_XML)
	public User deleteUser(@PathParam("username") String username) throws Exception{
		UserInterface user =  (UserInterface) appContext.getBean("UserInterface");
		User deluser = user.getUser(username);
		userService.deleteUser(deluser);
		return deluser;
		 
	}
	

}