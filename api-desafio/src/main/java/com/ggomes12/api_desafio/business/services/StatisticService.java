package com.ggomes12.api_desafio.business.services;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ggomes12.api_desafio.controllers.dtos.StatisticResponseDTO;
import com.ggomes12.api_desafio.controllers.dtos.TransactionRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatisticService {
	
	public final TransactionService transactionService;
	
	public StatisticResponseDTO calculateStatistics(Integer searchInterval) {
        
        List<TransactionRequestDTO> transactions = transactionService.searchTransactions(searchInterval);
        
        DoubleSummaryStatistics statisticTransactions = transactions.stream()
    		   .mapToDouble(TransactionRequestDTO::valor).summaryStatistics();
        
		if (transactions.isEmpty()) {
			return new StatisticResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
		}
        
		return new StatisticResponseDTO(statisticTransactions.getCount(), 
				statisticTransactions.getSum(),
				statisticTransactions.getAverage(), 
				statisticTransactions.getMin(), 
				statisticTransactions.getMax());
        
	}
	

}
