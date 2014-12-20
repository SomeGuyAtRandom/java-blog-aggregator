package la.random.spring.web.service;

import java.util.List;

import la.random.spring.web.entity.User;
import la.random.spring.web.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

	private static final String className = "UserService";

	public UserService() {
		System.out.println(className + "()");
	}
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
}
