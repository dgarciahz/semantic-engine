package investment.foundations;

import yahoofinance.*;
import yahoofinance.Stock;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;

import libs.cache.Cache;
import libs.cache.CacheLoaderITF;
import libs.utils.Trace;

import java.util.Date;

public class YahooFinancialLoader implements CacheLoaderITF {
	
	private LinkedList<String> yahooStocksList=new LinkedList();
	
	public void initialStocksList(String[] values) {
		for(int i=0;i<values.length;i++) {
			this.yahooStocksList.add(values[i]);			
		}
	}
	
	public void initialLoad(Cache cache) throws IOException {
		if(Trace.VERBOSE_TRACE)Trace.tracing(null, this, "initialLoad : iniciando la carga de "+this.yahooStocksList.size()+" valores");
		for(int i=0;i<this.yahooStocksList.size();i++) {
			if(Trace.VERBOSE_TRACE)Trace.tracing(null, this, "initialLoad : cargando "+this.yahooStocksList.get(i));
			if(cache.get(this.yahooStocksList.get(i))==null) cache.retrieve(this.yahooStocksList.get(i));
		}
		if(Trace.TRACE)Trace.tracing(null, this, "initialLoad : completada");
	}
	
//	ponmela y retornala cuando no la tengo (de cero)
	public Object retrieve(String key,Cache cache) throws IOException {
		if(Trace.VERBOSE_TRACE)Trace.tracing(null, this, "retrieve : trying "+key);
		yahoofinance.Stock stockYahoo = YahooFinance.get(key);
		investment.foundations.Stock stock=new investment.foundations.Stock(stockYahoo.getName(),key);
		if(Trace.VERBOSE_TRACE)Trace.tracing(null, this, "retrieve : retrieved from Yahoo "+key);
		StockDataUnit tempStock= new StockDataUnit();
		tempStock.setAsk(stockYahoo.getQuote().getAsk());
		tempStock.setBid(stockYahoo.getQuote().getBid());
		tempStock.setOpenValue(stockYahoo.getQuote().getOpen());
		tempStock.setVolume(new BigDecimal(Float.toString(stockYahoo.getQuote().getVolume())));
		stock.addDataUnit(tempStock);
		cache.add(key, stock);
		if(Trace.VERBOSE_TRACE)Trace.tracing(null, this, "retrieve : added to the cache "+key);		
		return stock;
	}
	
	/**
	public void expandStock(String ticketName) throws IOException{
		// Esto es post JSON
		investment.foundations.Stock stock=(Stock) this.cache.get(ticketName);
		Date lastDate=stock.getLastDate();

		if(lastDate.getTime())
	}


	private void updateStockDataUnit(StockDataUnit du) {
		StockDataUnit tempStock= new StockDataUnit();
		yahoofinance.Stock stockIn = YahooFinance.get(this.yahooStocksList.get(i));
		tempStock.setAsk(stockIn.getQuote().getAsk());
		tempStock.setBid(stockIn.getQuote().getBid());
		tempStock.setOpenValue(stockIn.getQuote().getOpen());
		tempStock.setVolume(new BigDecimal(Float.toString(stockIn.getQuote().getVolume())));
		cache.add(this.yahooStocksList.get(i), stockIn);
		stock.print();		
	}
*/
}
