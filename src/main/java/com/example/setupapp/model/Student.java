package com.example.setupapp.model;

//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;


//@Entity
//@Table(name = "student")
public class Student {
	
//	@Column(name = "id")
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
//	@Column(name = "username")
	String username;
	
//	@Column(name = "password")
	String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
