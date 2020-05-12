package com.soma.stocks.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.ta4j.core.indicators.statistics.PearsonCorrelationIndicator;

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
		 
		Stock google = YahooFinance.get(stockName);
		List<HistoricalQuote> googleHistQuotes = google.getHistory(from, to, Interval.DAILY);
		// googleHistQuotes is the same as google.getHistory() at this point
		// provide some parameters to the getHistory method to send a new request to Yahoo Finance
		if (google == null) {
			System.out.println("La accion " + stockName + " no esta en Yahoo :(");
		}
		
		List<HistoricalQuote> listahistorica = google.getHistory(Interval.DAILY);

		BigDecimal price = google.getQuote().getPrice();
		
		

		System.out.println("Stock: " + google);
		return googleHistQuotes;
		
	}
	
	
	 

}
