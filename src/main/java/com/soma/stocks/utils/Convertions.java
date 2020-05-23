package com.soma.stocks.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import yahoofinance.histquotes.HistoricalQuote;

public class Convertions {
	
	public static double[] getDoubleArrayStockPrices (List<HistoricalQuote> stockHistQuotes) {
		
		//Viene una lista de datos historicos
		List<BigDecimal> getStockData = new ArrayList<BigDecimal>();
		getStockData.add(BigDecimal.ZERO);
		
		stockHistQuotes.forEach(historicalQuote -> {
			getStockData.add(historicalQuote.getClose());
			getStockData.add(historicalQuote.getOpen());
			getStockData.add(historicalQuote.getHigh());
			getStockData.add(historicalQuote.getLow());
		});
		
		//Creo un array con la lista
		Object[] arrayStockQuotes = new BigDecimal[stockHistQuotes.size()];
		Arrays.fill(arrayStockQuotes, BigDecimal.ZERO);
		arrayStockQuotes= getStockData.toArray();
		
		//Convierto el array de BigDecimals a un array de doubles
		String[] stringArray = new String[arrayStockQuotes.length];
		for (int i = 0; i < arrayStockQuotes.length; i++) {
			stringArray[i] = arrayStockQuotes[i].toString();
		}

		double[] doubleArray = new double[stringArray.length];
		for (int i = 0; i < arrayStockQuotes.length; i++) {
			doubleArray[i] = Double.valueOf(stringArray[i]);
		}
		
		return doubleArray;
	}

}
