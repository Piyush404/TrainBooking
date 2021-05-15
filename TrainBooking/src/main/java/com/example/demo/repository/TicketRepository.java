package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

	List<Ticket> findByUserId(Long userId);

	
}