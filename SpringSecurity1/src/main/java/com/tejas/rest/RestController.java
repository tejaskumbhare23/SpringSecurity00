package com.tejas.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tejas.entity.User;
import com.tejas.repo.UserRepository;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	UserRepository repo;
	
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole("USER"); 
		User save = repo.save(user);
		
		return new ResponseEntity<User>(save,HttpStatus.CREATED);
		
		
	}
}
