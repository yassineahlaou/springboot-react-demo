package com.ahlaoudemo.pro.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahlaoudemo.pro.entities.User;
import com.ahlaoudemo.pro.repositories.UserRepository;

@RestController
public class UserController {
	@Autowired 
	private UserRepository userRepo; // the interface
	@GetMapping("/api/users")
	public List <User> users(){
		return userRepo.findAll();
		
	}


}
