package com.hollywood.sample.rest.server.json;

import java.util.Arrays;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.hollywood.sample.rest.server.dao.UserManagerDao;
import com.hollywood.sample.rest.server.model.UserRequest;
import com.hollywood.sample.rest.server.model.UserResponse;
import com.hollywood.sample.rest.server.webservice.UserManager;

@Consumes("application/json")
@Produces("application/json")
@Path("/")
public class UserManagerService implements UserManager {

	private UserManagerDao userDao;

	public UserManagerDao getUserDao()
	{
		return userDao;
	}

	public void setUserDao(UserManagerDao userDao)
	{
		this.userDao = userDao;
	}
	
	@Override
	@GET
	@Path("/user/{id}")
	public UserResponse fetchUserById(@PathParam("id") Integer id) {
		UserResponse response = new UserResponse();

		try
		{
			response.setUsers(Arrays.asList(getUserDao().fetchUserById(
				id)));
		}
		catch (Exception e)
		{
			response.setSuccess(false);
			response.setErrorMessage(e.getClass() + ": " + e.getMessage());
		}

		return response;
	}

	@GET
	@Path("/users/")
	public UserResponse fetchAllUsers() {
		UserResponse response = new UserResponse();

		try
		{
			response.setUsers(getUserDao().fetchAllUsers());
		}
		catch (Exception e)
		{
			response.setSuccess(false);
			response.setErrorMessage(e.getClass() + ": " + e.getMessage());
		}

		return response;
	}

	@Override
	@PUT
	@Path("/user")
	public UserResponse insertUser(UserRequest request) {
		UserResponse response = new UserResponse();

		try
		{
			getUserDao().insertUser(request.getUser());
			response.setUsers(Arrays.asList(request.getUser()));
		}
		catch (Exception e)
		{
			response.setSuccess(false);
			response.setErrorMessage(e.getClass() + ": " + e.getMessage());
		}

		return response;
	}

	@PUT
	@Path("/user/{id}")
	public UserResponse updateUser(@PathParam("id") Integer id, UserRequest request) {
		UserResponse response = new UserResponse();

		try
		{
			if ( !id.equals(request.getUser().getId()))
			{
				throw new IllegalAccessException("Id on path does not match the user id");
			}
			
			getUserDao().updateUser(request.getUser());
		}
		catch (Exception e)
		{
			response.setSuccess(false);
			response.setErrorMessage(e.getClass() + ": " + e.getMessage());
		}

		return response;
	}

	@DELETE
	@Path("/deleteUser/")
	public UserResponse deleteUser(UserRequest request) {
		UserResponse response = new UserResponse();

		try
		{
			getUserDao().deleteUser(request.getUser());
		}
		catch (Exception e)
		{
			response.setSuccess(false);
			response.setErrorMessage(e.getClass() + ": " + e.getMessage());
		}

		return response;
	}

}
