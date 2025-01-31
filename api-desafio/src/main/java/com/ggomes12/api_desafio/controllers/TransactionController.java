package com.ggomes12.api_desafio.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ggomes12.api_desafio.business.services.TransactionService;
import com.ggomes12.api_desafio.controllers.dtos.TransactionRequestDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor
public class TransactionController {
	
	private final TransactionService transactionService;
	
	@PostMapping
	public ResponseEntity<Void> addTransaction(@RequestBody TransactionRequestDTO dto) {
		
		transactionService.addTransaction(dto);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	@DeleteMapping
	public ResponseEntity<Void> clearTransactions() {

		transactionService.clearTransactions();

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
