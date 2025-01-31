package com.ggomes12.api_desafio.business.services;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ggomes12.api_desafio.controllers.dtos.TransactionRequestDTO;
import com.ggomes12.api_desafio.infrastructure.exceptions.UnprocessableEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

	
	private final List<TransactionRequestDTO> transactionsList = new ArrayList<>();
	
	public void addTransaction(TransactionRequestDTO dto) {
		
		log.info("Adding transaction: {}", dto);
		
		if(dto.dataHora().isAfter(OffsetDateTime.now())) {
			log.error("Transaction date and time cannot be in the future");
            throw new UnprocessableEntity("Transaction date and time cannot be in the future");
		}
		
		if(dto.valor() < 0) {
			log.error("Transaction value cannot be negative");
            throw new UnprocessableEntity("Transaction value cannot be negative");
        }
		
		transactionsList.add(dto);
		log.info("Transaction added successfully");
	}
	
	
	public void clearTransactions() {
		log.info("Clearing transactions list ");
		transactionsList.clear();
		log.info("Transactions list cleared successfully");
	}
	
	
	public List<TransactionRequestDTO> searchTransactions(Integer searchInterval) {
		
		log.info("Searching transactions in the last {} seconds", searchInterval);
		OffsetDateTime dateTimeInterval = OffsetDateTime.now().minusSeconds(searchInterval);
		
		
		log.info("Returning success transactions");
		return transactionsList.stream().filter(transaction -> transaction.dataHora().
				isAfter(dateTimeInterval)).toList();
		
	}
	
	
	
}
