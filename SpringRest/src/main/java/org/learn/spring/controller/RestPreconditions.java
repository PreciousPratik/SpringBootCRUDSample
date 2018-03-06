package org.learn.spring.controller;

import org.learn.spring.exception.ResourceNotFoundException;

public class RestPreconditions {
	public static <T> T checkFound(T resource) {
		if (resource == null) {
			throw new ResourceNotFoundException();
		}
		return resource;
	}
}
