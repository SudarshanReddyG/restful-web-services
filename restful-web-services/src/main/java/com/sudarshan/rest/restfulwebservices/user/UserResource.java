package com.sudarshan.rest.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sudarshan.rest.restfulwebservices.user.exception.UserNotFoundException;

//The below annotation is same as @Controller and @ResponseBody 
//Here @ResponseBody annotation searches for Message Converter to represent object into JSON form
@RestController
public class UserResource {
	
	private UserDaoService userDaoService;
	
	public UserResource(UserDaoService userDaoService) {
		this.userDaoService = userDaoService; 
	}
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userDaoService.findAll();
	}
	
	@GetMapping("/users/{userId}")
	public User retrieveUser(@PathVariable Integer userId) {
		User user = userDaoService.findOne(userId);
		if(user == null) 
			throw new UserNotFoundException("id = "+userId);
		
		return user;
	}
	
	//@RequestBody is used to Map input Post parameters to Object specified
	//@RequestBody requires a default constructor for deserializing the data, and input data should be in JSON format
	//ResponseEntity is used for customized Response.
	//ServletUriComponentsBuilder is used build the URI from current request.
	//It is good practice to send HTTP code 201 for new creation and the URL for resource should be returned. It will be placed
	//in header with name location by ResponseEntity Object.
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = userDaoService.save(user);
		
		URI newUserUri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId())
			.toUri();
		
		return ResponseEntity.created(newUserUri).build();
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUserById(@PathVariable Integer id) {
		User userToDelete = userDaoService.deleteById(id);
		
		if(userToDelete == null) 
			throw new UserNotFoundException("id = "+id);
		
		return ResponseEntity.noContent().build();
	}
	
}
