package com.ggomes12.api_desafio.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ggomes12.api_desafio.business.services.StatisticService;
import com.ggomes12.api_desafio.controllers.dtos.StatisticResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
public class StatisticController {

	private final StatisticService statisticService;

	 @GetMapping
	 public ResponseEntity<StatisticResponseDTO> getStatistics(@RequestParam(value = "searchInterval", 
	 												required = false, defaultValue = "60") 
	 													Integer searchInterval) {
		 
		 statisticService.calculateStatistics(searchInterval);
		 
		 return ResponseEntity.ok(statisticService.calculateStatistics(searchInterval));
	 }
}
