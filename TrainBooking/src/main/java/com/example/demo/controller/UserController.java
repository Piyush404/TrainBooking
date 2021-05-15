package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TrainDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(TrainController.class);

	@Autowired
	UserService userService;

	@PostMapping
	public ResponseEntity<?> saveUser(@Valid @RequestBody UserDTO userDTO) {
		UserDTO userDTO1 = userService.saveUser(userDTO);
		logger.info("Saving Users");
		return new ResponseEntity<UserDTO>(userDTO1, HttpStatus.CREATED);
	}

	@GetMapping
	public List<UserDTO> getAllUsersByPageSizeAndPageNum(@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "3") int pageSize) {

		logger.info("Fetching Users");
		return userService.getAllUsersByPageSizeAndPageNum(pageNumber, pageSize);
	}

	@GetMapping("/{userId}")
	public UserDTO getUserById(@Valid @PathVariable Long userId) {
		UserDTO userDTO1 = new UserDTO();
		BeanUtils.copyProperties(userService.getUserById(userId), userDTO1);
		return userDTO1;
	}

	@PostMapping("/{userId}")
	public UserDTO updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
		UserDTO userDTO1 = new UserDTO();
		BeanUtils.copyProperties(userService.updateUser(userId, userDTO), userDTO1);
		return userDTO1;

	}


}
