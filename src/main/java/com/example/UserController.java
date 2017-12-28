package com.example;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;
	final static Logger logger = Logger.getLogger(UserController.class);

	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public ResponseEntity<Object> getUsers() {

		Response response = null;
		List<User> users = userService.getUsers();

		if (users == null) {
			response = new Response();
			response.setMessage("User Not Found");
			return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Object>(users, HttpStatus.OK);
	}
}