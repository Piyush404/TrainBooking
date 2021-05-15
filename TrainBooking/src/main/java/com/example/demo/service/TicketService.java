package com.example.demo.service;



import java.util.List;

import com.example.demo.dto.TicketDTO;
import com.example.demo.entity.Ticket;

public interface TicketService {

	public TicketDTO saveTicket(TicketDTO ticketDTO);

	public List<TicketDTO> getTicketByUserId(Long userId) throws Exception;

	

	//public void deleteTicketById(Long ticketId);

	//public Ticket updateTicket(Long ticketId, Ticket ticket) throws Exception;

}
