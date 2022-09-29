package com.ahlaoudemo.pro.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data //generate getters and setters
@NoArgsConstructor
@AllArgsConstructor
@ToString
//les annotations help hibernate to create table (mapping)
@Entity  //means this class is for a table in database
@Table (name="users") //if we don't give a name it will take the same name as the class name
public class User {
	
	@Id // identifiant attribute
	@GeneratedValue(strategy = GenerationType.IDENTITY) //this will increment the id automaticly
	
	 private Long id; //id here is a class not primitive , so we used Long not long
	@Column(name="firstname", length=25) //give a column name
	//@NotNull(message="{NotNull.User.email}")
	    private String firstName;
	    
	  @Column(name="lastname" , length=25)
	    private String lastName; 
	    
	  @Column(name="email")
	    private String email;
	  
	  @Column(name="age")
	    private int age;
	  
	  @Column(name="paymentDate")
	  @Temporal(TemporalType.DATE)//specify date format
	  private  Date PaymentDate;
	    
}
