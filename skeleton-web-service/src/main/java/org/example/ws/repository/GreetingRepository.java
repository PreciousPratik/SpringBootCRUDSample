package org.example.ws.repository;

import java.math.BigInteger;

import org.example.ws.model.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GreetingRepository extends JpaRepository<Greeting, Long> {

}
