package com.ahlaoudemo.pro.repositories;  //it's like DAO

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ahlaoudemo.pro.entities.User;



public interface UserRepository extends JpaRepository<User,Long> {

	public List<User> findByage(int age);
	
	public List<User> findByfirstNameContains(String car);
	
	public Page<User> findBylastNameContains(String car, Pageable pageable );
	
	public List<User> findByfirstNameContainsAndLastNameContains(String car1, String car2);
	public List<User> findByfirstNameContainsAndAge(String car1, int age);
	
	/*User:what we want to handle
	Long : type of ID*/
	

}
