package com.hollywood.sample.rest.server.xml;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.hollywood.sample.rest.server.model.User;
import com.hollywood.sample.rest.server.webservice.HelloUserWebService;

@Path("xml")
@Produces("application/xml")
public class HelloUserXmlWebService implements HelloUserWebService {

	@GET
	@Path("/user/{userName}")
	public User greetUser(@PathParam("userName") String userName) {
		User user = new User();
		user.setFirstName(userName);
		return user;
	}

}