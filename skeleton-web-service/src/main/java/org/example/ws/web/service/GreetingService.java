package org.example.ws.web.service;

import java.util.Collection;

import org.example.ws.model.Greeting;

public interface GreetingService {
	
	public Collection<Greeting> findAll();
	
	public Greeting findOne(Long id);
	
	public Greeting createGreeting(Greeting greeting);
	
	public Greeting updateGreeting(Greeting greeting);

	public void deleteGreeting(Long id);
	
	public void evitCache(); 
}
