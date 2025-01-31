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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor
public class TransactionController {
	
	private final TransactionService transactionService;
	
	@PostMapping
	@Operation(description = "Add a new transaction")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Transaction added"),
			@ApiResponse(responseCode = "204", description = "Transaction older than 60 seconds"),
			@ApiResponse(responseCode = "400", description = "Invalid transaction data"),
			@ApiResponse(responseCode = "422", description = "Transaction timestamp is in the future"),
			@ApiResponse(responseCode = "500", description = "Internal server error")	
	})
	public ResponseEntity<Void> addTransaction(@RequestBody TransactionRequestDTO dto) {
		
		transactionService.addTransaction(dto);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	@DeleteMapping
	@Operation(description = "Clear all transactions")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Transactions cleared"),
			@ApiResponse(responseCode = "400", description = "Invalid transaction data"),
			@ApiResponse(responseCode = "500", description = "Internal server error")	
			})
	public ResponseEntity<Void> clearTransactions() {

		transactionService.clearTransactions();

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
