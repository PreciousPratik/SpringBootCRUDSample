package org.learn.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.learn.spring.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{
	
	private static List<User> users;  
	private static final AtomicLong counter = new AtomicLong();
	static {
		users = bootstrapDB(); 
	}

	public List<User> findAllUsers() {
		return users;
	}

	public User findOne(long id) {
		for (User user : users) {
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}

	public boolean create(User user) {
		return users.add(user);
	}

	private static List<User> bootstrapDB() {
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(), "Tom", "tom.j@gmail.com", 25)); 
		users.add(new User(counter.incrementAndGet(), "Sam", "sam.f@gmail.com", 36)); 
		users.add(new User(counter.incrementAndGet(), "Jerome", "jerome.qwe@gmail.com", 47)); 
		users.add(new User(counter.incrementAndGet(), "Silvia", "silvia@gmail.com", 69)); 
		return users;
	}
}
