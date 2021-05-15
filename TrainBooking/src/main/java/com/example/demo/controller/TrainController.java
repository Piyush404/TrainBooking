package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TicketDTO;
import com.example.demo.dto.TrainDTO;
import com.example.demo.entity.Train;
import com.example.demo.exception.handling.MessageResponse;
import com.example.demo.service.TrainService;

@RestController
@RequestMapping("/trains")
public class TrainController {

	private final Logger logger = LoggerFactory.getLogger(TrainController.class);

	@Autowired
	TrainService trainService;

	@PostMapping
	public ResponseEntity<?> saveTrain(@RequestBody TrainDTO trainDTO) {

		TrainDTO trainDTO1 = trainService.saveTrain(trainDTO);
		logger.info("Saving Train");
		return new ResponseEntity<TrainDTO>(trainDTO1, HttpStatus.CREATED);
	}

	

	@GetMapping()
	public ResponseEntity<?> findTrainByDate(@RequestParam String source, @RequestParam String destination,
			@RequestParam String date) throws Exception {
		List<TrainDTO> ticketDTO3 = trainService.findTrainBySourceContainsAndDestinationContainsAndStartDate(source,
				destination, date);
		if (ObjectUtils.isEmpty(ticketDTO3)) {

			logger.info("Train is not Valid");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new MessageResponse("Please enter valid details"));
		}
		return new ResponseEntity<List<TrainDTO>>(ticketDTO3, HttpStatus.OK);

	}

	

}
