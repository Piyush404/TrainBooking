package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TrainDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private final Logger logger = LoggerFactory.getLogger(TrainServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDTO saveUser(UserDTO userDTO) {
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		
		User saveuser= new User();
		UserDTO userDTO1= new UserDTO();
		BeanUtils.copyProperties(saveuser, userDTO1);
		return userDTO1;
	}

	@Override
	public List<UserDTO>  getAllUsersByPageSizeAndPageNum(int pageNumber, int pageSize) {
		   Pageable pageable = PageRequest.of(pageNumber, pageSize);
	        List<UserDTO> userDTOList = new ArrayList<UserDTO>();
	        List<User> userList  = userRepository.findAll(pageable).getContent();
	        
	        for (User user : userList) {
	            UserDTO userDTO = new UserDTO();
	            BeanUtils.copyProperties(user, userDTO);
	            userDTOList.add(userDTO);
	        }
	        logger.info("User Found");
	        
		return userDTOList;
	}

	@Override
	public User getUserById(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			return user.get();
		}
		logger.info("User Found");
		return null;
	}

	@Override
	public User updateUser(Long userId, UserDTO userDTO) {

		User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		
		User dbUser = getUserById(userId);
		dbUser.setUsername(user.getUsername());
		dbUser.setContactNo(user.getContactNo());
		dbUser.setEmail(user.getEmail());
		dbUser.setAge(user.getAge());

		logger.info("User Updated");
		return userRepository.save(dbUser);
	
	}

	@Override
	public void deleteUserById(Long userId) {
		userRepository.deleteById(userId);

	}

	@Override
	public List<User> getUsersByName(String username, String password) {
		
		List<User> users = userRepository.findByUsernameContainsOrPasswordContains(username, password);
		
		return users;
	}

	
}
