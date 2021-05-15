package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.dto.TrainDTO;
import com.example.demo.entity.Train;


public interface TrainRepository extends JpaRepository<Train, Long> {


List<Train> findTrainBySourceAndDestination(String source, String destination);

List<Train> findTrainBySourceContainsAndDestinationContainsAndStartDate(String source, String destination, LocalDate localDate);

List<Train> findBySourceAndDestinationAndStartTime(String source, String destination, String time);

@Modifying
@Query("update Train t set t.availableSeats=:availableSeats where t.trainId =:trainId")
void updateTrain(@Param("availableSeats") int availableSeats,@Param("trainId")long trainId);


	
	
}
