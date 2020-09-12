package com.soma.stocks.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.core.Conventions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.soma.stocks.utils.Convertions;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.jfree.util.Log;

@RestController
@Api(value="onlinestore")

public class StockController {
	
	/**
     * Return Stock Data!
     *
     * @return The response instance. Status Code: 200.
     */

	@ApiOperation(value = "Get Pearson Correlation between two stocks", response = ResponseEntity.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved Pearson Correlation value"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource stock"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	
	@GetMapping(value = "/stock/{stockName_1}/and/{stockName_2}", produces = "application/json")
	public String getPearsonCorrelation(@PathVariable String stockName_1, @PathVariable String stockName_2) throws Exception {
		
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.add(Calendar.YEAR, -2); // from 2 year ago
		 
		Log.info("Getting Stocks Data from Yahoo API");
		
		Stock stock_1 = YahooFinance.get(stockName_1);
		List<HistoricalQuote> stockHistQuotes_1 = stock_1.getHistory(from, to, Interval.DAILY);
		
		Stock stock_2 = YahooFinance.get(stockName_2);
		List<HistoricalQuote> stockHistQuotes_2 = stock_2.getHistory(from, to, Interval.DAILY);
		
		 Log.info("Convierto lista de llegada en array double: ");
		double[] arrayStockQuotes_1 = Convertions.getDoubleArrayStockPrices(stockHistQuotes_1);
		double[] arrayStockQuotes_2 = Convertions.getDoubleArrayStockPrices(stockHistQuotes_2);
		
	    Log.info("Realizo la correlacion de Pearson: ");
	    double corr = new PearsonsCorrelation().correlation(arrayStockQuotes_1, arrayStockQuotes_2);
	    System.out.println(corr);
	    
		return String.valueOf(corr);
		
	}
	
	@GetMapping(value = "/stock/{stockName_1}/and/{stockName_2}/{from}", produces = "application/json")
	public double getPearsonCorrelationFromn(@PathVariable String stockName_1,
			@PathVariable String stockName_2, @PathVariable String from) throws Exception {
		
		Calendar to = Calendar.getInstance();
		//1. Create a Date from String
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		String dateInString = from;
		Date date = sdf.parse(dateInString);
		Calendar fromCal = Calendar.getInstance();
		fromCal.setTime(date);

		Log.info("Getting Stocks Data from Yahoo API");
		
		Stock stock_1 = YahooFinance.get(stockName_1);
		List<HistoricalQuote> stockHistQuotes_1 = stock_1.getHistory(fromCal, to, Interval.DAILY);
		
		Stock stock_2 = YahooFinance.get(stockName_2);
		List<HistoricalQuote> stockHistQuotes_2 = stock_2.getHistory(fromCal, to, Interval.DAILY);
		
		 Log.info("Convierto lista de llegada en array double: ");
		double[] arrayStockQuotes_1 = Convertions.getDoubleArrayStockPrices(stockHistQuotes_1);
		double[] arrayStockQuotes_2 = Convertions.getDoubleArrayStockPrices(stockHistQuotes_2);
		
	    Log.info("Realizo la correlacion de Pearson: ");
	    double corr = new PearsonsCorrelation().correlation(arrayStockQuotes_1, arrayStockQuotes_2);
	    System.out.println(corr);
	    
		return corr;
		
	}


}
