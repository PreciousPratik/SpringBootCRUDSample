package org.example.ws.web.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.example.ws.model.Greeting;
import org.example.ws.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GreetingServiceBean implements GreetingService {
	
	Map map = new HashMap<>();  
	@Autowired
	GreetingRepository repository;
	
	@Override
	public Collection<Greeting> findAll() {
		Collection<Greeting> greetings = repository.findAll();
		return greetings;
	}

	@Override
	@Cacheable(value = "greetings", key = "#id")
	public Greeting findOne(Long id) {
		Greeting greeting = repository.findOne(id);
		return greeting;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CachePut(value = "greetings", key = "#result.id")
	public Greeting createGreeting(Greeting greeting) {
		if (greeting.getId() != null) {
            // Cannot create Greeting with specified ID value
            return null;
        }
		Greeting g1 = repository.save(greeting);
		return g1;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CachePut(value = "greetings", key = "#greeting.id")
	public Greeting updateGreeting(Greeting greeting) {
		Greeting persisted = findOne(greeting.getId());
		if(persisted == null) {
			return null;
		}
		Greeting g2 = repository.save(greeting);
		return g2;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CacheEvict(value="greetings", key="#id")
	public void deleteGreeting(Long id) {
		repository.delete(id);
	}

	@Override
	@CacheEvict(value="greetings", allEntries = true)
	public void evitCache() {
		
	}

}
