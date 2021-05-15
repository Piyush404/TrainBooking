package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TicketDTO;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.handling.MessageResponse;
import com.example.demo.service.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {

	private final Logger logger = LoggerFactory.getLogger(TrainController.class);

	@Autowired
	TicketService ticketService;

	@PostMapping("")
	@Validated
	public ResponseEntity<?> saveTicket(@Valid @RequestBody TicketDTO ticketDTO) throws UserNotFoundException {
		TicketDTO ticketDTO2 = ticketService.saveTicket(ticketDTO);
		logger.info("Ticket Saved");
		return new ResponseEntity<TicketDTO>(ticketDTO2, HttpStatus.CREATED);

	}


	@GetMapping("/{userId}")
	public ResponseEntity<?>  getTicketById(@Valid @PathVariable Long userId) throws Exception {
		List<TicketDTO> ticketDTO3 = ticketService.getTicketByUserId(userId);
		if (ObjectUtils.isEmpty(ticketDTO3)) {

			logger.info("UserId is not Valid");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Please enter valid userId"));
		}
		return new ResponseEntity<List<TicketDTO>>(ticketDTO3, HttpStatus.OK);

		
	}

	/*
	 * @DeleteMapping("/{ticketId}") public void deleteTicketById(@PathVariable Long
	 * ticketId) { ticketService.deleteTicketById(ticketId); }
	 */

	/*
	 * @GetMapping("/{userId}") public TicketDTO BookingHistoryById(@PathVariable
	 * Long userId) { return ticketService.bookingHistoryById(userId); }
	 */

}
