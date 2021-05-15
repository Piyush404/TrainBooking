package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

	List<User> findByUsernameAndPassword(String username, String password);

	List<User> findByUsernameContainsOrPasswordContains(String username, String password);

	List<User> findByUsernameOrPassword(String username, String password);

	



}
