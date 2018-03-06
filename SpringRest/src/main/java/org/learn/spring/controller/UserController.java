package org.learn.spring.controller;

import java.util.List;

import org.learn.spring.model.User;
import org.learn.spring.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user") 
public class UserController {
	
	@Autowired
	private IUserService service;
	
	@RequestMapping(value="/hello")
	public String hello(){
		return "<h2>Hello</h2>";
	}
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<User> getAllUsers(){
		List<User> users = service.findAllUsers();
		return users;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable long id){
		User user = service.findOne(id);
		RestPreconditions.checkFound(user);
		return user;
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.CREATED)
	public boolean create(@RequestBody User user){
		RestPreconditions.checkFound(user);
		return service.create(user);
	}
	
}