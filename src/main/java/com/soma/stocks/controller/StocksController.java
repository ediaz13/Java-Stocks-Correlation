package com.soma.stocks.controller;

import com.soma.stocks.utils.Convertions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.jfree.util.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@Api(value="onlinestore")

public class StocksController {
	
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
	
	@GetMapping(value = "/stocks/{idWallet}", produces = "application/json")
	public String getPearsonCorrelation(@PathVariable String stockName_1, @PathVariable String stockName_2) throws Exception {

		return "Nais";
		
	}

}
