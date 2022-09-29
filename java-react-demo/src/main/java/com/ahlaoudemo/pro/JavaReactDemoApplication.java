package com.ahlaoudemo.pro;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ahlaoudemo.pro.entities.User;
import com.ahlaoudemo.pro.repositories.UserRepository;

@SpringBootApplication
public class JavaReactDemoApplication implements CommandLineRunner {
	@Autowired //link to spring data , means gets an object
	private UserRepository userRepo; // the interface

	public static void main(String[] args) {
		SpringApplication.run(JavaReactDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//what will run just after spring started
		
		/*userRepo.save(new User(null, "yassine" , "ahlaou" , "yassineahlaou@gmail.com" , 23 , new Date() ));
		userRepo.save(new User(null, "saad" , "chahi" , "saad@gmail.com" , 30 , new Date() ));
		userRepo.save(new User(null, "ayoub" , "hanani" , "ayoub@gmail.com" , 30 , new Date() ));
		userRepo.save(new User(null, "yassine" , "ahlaou" , "yassineahlaou@gmail.com" , 23 , null ));
		userRepo.findAll().forEach(item->{
			System.out.println(item.toString());
			
		});
		System.out.println("*****************");
		
		User one = userRepo.findById(2L).get(); //L because it is type long
		System.out.println(one);
		
		System.out.println("*****************");
		
		userRepo.findByage(30).forEach(item->{
			System.out.println(item.toString());
			
		});
		System.out.println("*****************");
		userRepo.findByfirstNameContains("sa").forEach(item->{
			System.out.println(item.toString());
			
		});
		System.out.println("*****************");
		userRepo.findByfirstNameContainsAndLastNameContains("s","l").forEach(item->{
			System.out.println(item.toString());
			
		});
		System.out.println("*****************");
		userRepo.findByfirstNameContainsAndAge("s",23).forEach(item->{
			System.out.println(item.toString());
			
		});
		
		System.out.println("********Pagination********");
		Page<User> pageUsers =  userRepo.findAll(PageRequest.of(0,2));
		//(0,3) 0:la numero de la page, 3 nombre de data dans chaque page
		
		List<User> usersList = pageUsers.getContent();
		
		System.out.println("pages total:"+ pageUsers.getTotalPages());
		System.out.println("number of elements:"+ pageUsers.getNumberOfElements());
		
		usersList.forEach(item->{
			System.out.println(item.toString());
		});
		
		System.out.println("******Paginate using interface**********");
		Page<User> pagesUsers = userRepo.findBylastNameContains("a",PageRequest.of(1, 2));
		pagesUsers.forEach(item->{
			System.out.println(item.toString());
		});
		*/
	}

}
