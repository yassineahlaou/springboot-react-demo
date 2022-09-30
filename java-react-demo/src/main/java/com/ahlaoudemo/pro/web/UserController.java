package com.ahlaoudemo.pro.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahlaoudemo.pro.entities.User;
import com.ahlaoudemo.pro.exception.EmailAlreadyExistException;
import com.ahlaoudemo.pro.repositories.UserRepository;

@RestController
public class UserController {
	@Autowired 
	private UserRepository userRepo; // the interface
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/api/users")
	public List <User> users(){
		return userRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/getUser/{id}")
	public  ResponseEntity  getUser(@PathVariable Long id){
		User foundUser = userRepo.findById(id).orElseThrow(RuntimeException::new);
		
		return ResponseEntity.ok(foundUser);
		
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/addUser")
    public ResponseEntity createUser(@RequestBody User user) throws URISyntaxException {
		List<User> emailexist = userRepo.findByemail(user.getEmail());
		
		if (emailexist.size() != 0) {
			throw new EmailAlreadyExistException();
		}
		
        User savedUser = userRepo.save(user);
        //return ResponseEntity.created(new URI("/users" + savedUser.getId())).body(savedUser);
       return ResponseEntity.ok(savedUser);
    }
	
	@CrossOrigin(origins = "http://localhost:3000")
	
	 @PutMapping("/storeUpdate/{id}")
	    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody  User user) {
	        User currentUser = userRepo.findById(id).orElseThrow(RuntimeException::new);
	       
	        currentUser.setEmail(user.getEmail());
	        currentUser.setFirstName(user.getFirstName());
	        currentUser.setLastName(user.getLastName());
	        currentUser.setAge(user.getAge());
	        
	        userRepo.save(currentUser);
	        
	        return ResponseEntity.ok(currentUser);
	        
	        
	        }
	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("/deleteUser/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
