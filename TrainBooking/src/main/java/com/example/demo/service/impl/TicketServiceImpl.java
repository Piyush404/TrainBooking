package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.controller.TrainController;
import com.example.demo.dto.TicketDTO;

import com.example.demo.entity.Ticket;
import com.example.demo.entity.Train;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.TrainRepository;
import com.example.demo.service.TicketService;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {
	
	private final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	TrainRepository trainRepository;

	@Override
	public TicketDTO saveTicket(TicketDTO ticketDTO) {

		
		logger.info("Saving ticket initiated");
		Ticket ticket = new Ticket();
		BeanUtils.copyProperties(ticketDTO, ticket);
		ticketRepository.save(ticket);
		TicketDTO ticketDTO1 = new TicketDTO();
		BeanUtils.copyProperties(ticket, ticketDTO1);

		Optional<Train> train = trainRepository.findById(ticketDTO.getTrainId());
		Train savedTrain = train.get();
		int seats = savedTrain.getAvailableSeats() - ticketDTO.getPassenger().size();
		trainRepository.updateTrain(seats, ticketDTO.getTrainId());
		logger.info("Ticket Saved");
		return ticketDTO1;
	}

	@Override
	public List<TicketDTO> getTicketByUserId(Long userId) throws Exception {
		List<Ticket> ticket = ticketRepository.findByUserId(userId);

		if (ticket.isEmpty()) {

			return null;
		}
		List<TicketDTO> ticketDTO = new ArrayList<TicketDTO>();
		TicketDTO ticketDTO1 = new TicketDTO();
		for (Ticket ticket1 : ticket) {
			BeanUtils.copyProperties(ticket1, ticketDTO1);
			ticketDTO.add(ticketDTO1);
		}
		logger.info("Ticket Found");
		return ticketDTO;

	}



	/*
	 * @Override public void deleteTicketById(Long ticketId) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public Ticket updateTicket(Long ticketId, Ticket ticket) throws
	 * Exception { // TODO Auto-generated method stub return null; }
	 */

}
