package org.learn.spring.service;

import java.util.List;

import org.learn.spring.model.User;

public interface IUserService {

	List<User> findAllUsers();

	User findOne(long id);

	boolean create(User user);

}
