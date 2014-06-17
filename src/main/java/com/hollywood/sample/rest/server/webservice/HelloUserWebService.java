package com.hollywood.sample.rest.server.webservice;

import com.hollywood.sample.rest.server.model.User;

public interface HelloUserWebService {
	 //parameter that gets passed via the URL
	 User greetUser(String userName);
}
