package com.ahlaoudemo.pro.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahlaoudemo.pro.entities.User;
import com.ahlaoudemo.pro.repositories.UserRepository;

@RestController
public class UserController {
	@Autowired 
	private UserRepository userRepo; // the interface
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/api/users")
	public List <User> users(){
		return userRepo.findAll();
		
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/addUser")
    public ResponseEntity createClient(@RequestBody User user) throws URISyntaxException {
        User savedUser = userRepo.save(user);
        return ResponseEntity.created(new URI("/users" + savedUser.getId())).body(savedUser);
    }


}
