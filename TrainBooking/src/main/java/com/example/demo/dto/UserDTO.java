package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDTO {

	
	@NotEmpty(message="Please Enter Username")
	private String username;
	
	@NotEmpty(message="Please enter password")
	private String password;
	
	@NotEmpty(message="Please enter email")
	private String email;
	
	@NotEmpty(message="Please enter age")
	private long age;
	
	@NotEmpty(message="Please enter Contact Number")
	@Size(max=10,min=10)
	private String contactNo;
	
	@NotEmpty(message="Please enter role")
	private String role;
	
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getAge() {
		return age;
	}
	public void setAge(long age) {
		this.age = age;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
