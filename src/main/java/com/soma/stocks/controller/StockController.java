package com.soma.stocks.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

@RestController
@Api(value="onlinestore", description="Stocks!")

public class StockController {
	
	/**
     * Return Stock Data!
     *
     * @return The response instance. Status Code: 200.
     */

	@ApiOperation(value = "View stock data!", response = ResponseEntity.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved stock values"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource stock"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping(value = "/stock/{stockName}", produces = "application/json")
	public List<HistoricalQuote> getStockData(@PathVariable String stockName) throws IOException, Exception {
		
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.add(Calendar.YEAR, -5); // from 2 year ago
		 
		Stock stock = YahooFinance.get(stockName);
		List<HistoricalQuote> stockHistQuotes = stock.getHistory(from, to, Interval.DAILY);
		
		int tamañoLista = stockHistQuotes.size();
		
		
		//Elimino el campo fecha para hacer la correlacion
		stockHistQuotes.forEach(historicalQuote -> {
			historicalQuote.getDate().set(0, 0, 0);
		});
		
		List<HistoricalQuote> stockHistQuotes2 = stockHistQuotes;
		
		double[] stockHistQuotesArray;
		stockHistQuotesArray = new double[stockHistQuotes.size()];
		
//		stockHistQuotesArray[0]from.set(field, value);
		
		
		System.out.println("Stock: " + stock);
		System.out.println("Tamaño lista:" + tamañoLista);
		
		
//		double corr = new PearsonsCorrelation().	correlation(stockHistQuotes.toArray(), stockHistQuotes2.toArray());

		System.out.println("History:" + stockHistQuotes);
		
		double[] x = {1, 2, 4, -5};
	    double[] y = {-2, 4, 9, 234};
	    double corr = new PearsonsCorrelation().correlation(y, x);

	    System.out.println(corr);
		
		
		return stockHistQuotes;
		
	}
	
	
	 

}
