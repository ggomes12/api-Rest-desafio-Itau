package com.ggomes12.api_desafio.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ggomes12.api_desafio.business.services.StatisticService;
import com.ggomes12.api_desafio.controllers.dtos.StatisticResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
public class StatisticController {

	private final StatisticService statisticService;

	 @GetMapping
	 @Operation(description = "Get statistics of transactions")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Statistics retrieved"),
				@ApiResponse(responseCode = "400", description = "Invalid search interval"),
				@ApiResponse(responseCode = "500", description = "Internal server error")
				})
	 public ResponseEntity<StatisticResponseDTO> getStatistics(@RequestParam(value = "searchInterval", 
	 												required = false, defaultValue = "60") 
	 													Integer searchInterval) {
		 
		 StatisticResponseDTO statistics = statisticService.calculateStatistics(searchInterval);
		 
		 return ResponseEntity.ok(statistics);
	 }
}
