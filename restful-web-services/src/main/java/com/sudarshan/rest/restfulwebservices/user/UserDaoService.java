package com.sudarshan.rest.restfulwebservices.user;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users = new CopyOnWriteArrayList<>();
	
	static {
		users.add(new User(1, "Ram", new Date()));
		users.add(new User(2, "Raheem", new Date()));
		users.add(new User(3, "Robert", new Date()));
	}
	
	private static AtomicInteger userCount = new AtomicInteger(users.size());
	
	public List<User> findAll() {
		return users;
	}
	
	public User findOne(Integer id) {
		Stream<User> userStream = users.parallelStream().filter(user -> user.getId() == id);
		Optional<User> optionalUser = userStream.findFirst();
		return  optionalUser.isPresent() ? optionalUser.get() : null;
	}
	
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(userCount.incrementAndGet());
		}
		users.add(user);
		return user;
	}
	
	public User deleteById(Integer id) {
		User userToDelete = findOne(id);
		if(userToDelete == null)
			return null;
		else 
			users.remove(userToDelete);
		
		return userToDelete;
		
	}
	
}
