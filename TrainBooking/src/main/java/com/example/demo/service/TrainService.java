package com.example.demo.service;


import java.util.List;

import com.example.demo.dto.TrainDTO;
import com.example.demo.entity.Train;



public interface TrainService {

	public TrainDTO saveTrain(TrainDTO trainDTO);

	public List<Train> findTrainBySourceAndDestination(String source, String destination);

	public List<TrainDTO> findTrainBySourceContainsAndDestinationContainsAndStartDate(String source, String destination, String date);

	public List<Train> findBySourceAndDestinationAndStartTime(String source, String destination, String time);

	
	
	

}
