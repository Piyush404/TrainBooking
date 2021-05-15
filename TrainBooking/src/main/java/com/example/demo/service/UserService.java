package com.example.demo.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;


public interface UserService {
	
	

	public UserDTO saveUser(UserDTO userDTO);

	
	public User getUserById(Long userId);
	
	public User updateUser(Long userId, UserDTO userDTO);
	
	public void deleteUserById(@PathVariable Long userId);

	public List<User> getUsersByName(String username, String password);

	public List<UserDTO>  getAllUsersByPageSizeAndPageNum(int pageNumber, int pageSize);	
	

	

}
