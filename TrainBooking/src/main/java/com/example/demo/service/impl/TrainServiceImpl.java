package com.example.demo.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TrainDTO;
import com.example.demo.entity.Train;
import com.example.demo.repository.TrainRepository;
import com.example.demo.service.TrainService;

@Service
public class TrainServiceImpl implements TrainService {
	
	private final Logger logger = LoggerFactory.getLogger(TrainServiceImpl.class);

	@Autowired
	TrainRepository trainRepository;

	@Override
	public TrainDTO saveTrain(TrainDTO trainDTO) {
		
		logger.info("Train Initiated");
		Train train = new Train();
		BeanUtils.copyProperties(trainDTO, train);
		
		Train savetrain= trainRepository.save(train);
		TrainDTO trainDTO1= new TrainDTO();
		BeanUtils.copyProperties(savetrain, trainDTO1);
		logger.info("Train Saved");
		return trainDTO1;
		
	}

	@Override
	public List<Train> findTrainBySourceAndDestination(String source, String destination) {
		return trainRepository.findTrainBySourceAndDestination(source, destination);
	}

	@Override
	public List<TrainDTO> findTrainBySourceContainsAndDestinationContainsAndStartDate(String source, String destination, String date) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
		
		
		List<Train> trains = trainRepository.findTrainBySourceContainsAndDestinationContainsAndStartDate(source, destination, localDate);
		List<TrainDTO> dtos = new ArrayList<>();
		for (Train train : trains) {
		TrainDTO dto = new TrainDTO();
		BeanUtils.copyProperties(train, dto);
		dtos.add(dto);
		}
		logger.info("Train Found");
		return dtos;
	}

	@Override
	public List<Train> findBySourceAndDestinationAndStartTime(String source, String destination, String time) {
		return trainRepository.findBySourceAndDestinationAndStartTime(source, destination, time);
	}

}
