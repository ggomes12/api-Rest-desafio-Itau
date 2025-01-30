package com.ggomes12.api_desafio.business.services;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ggomes12.api_desafio.controllers.dtos.TransactionRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

	
	private final List<TransactionRequestDTO> transactionsList = new ArrayList<>();
	
	public void addTransaction(TransactionRequestDTO dto) {
		
		if(dto.dataHora().isAfter(OffsetDateTime.now())) {
            throw new IllegalArgumentException();
		}
		
		if(dto.valor() < 0) {
            throw new IllegalArgumentException();
        }
		
		transactionsList.add(dto);
	}
	
public void clearTransactions() {
		
		transactionsList.clear();
	}
	
	public List<TransactionRequestDTO> searchTransactions(Integer searchInterval) {
		
		OffsetDateTime dateTimeInterval = OffsetDateTime.now().minusSeconds(searchInterval);
		
		return transactionsList.stream().filter(transaction -> transaction.dataHora().
				isAfter(dateTimeInterval)).toList();
		
	}
	
	
	
}
