package investment.foundations;

import java.io.File;
import com.fasterxml.jackson.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Date;

public class Stock {
	private String companyName=null;
	private String tradingTicketName=null;
	private LinkedList<StockDataUnit> history=null;
	
	public Stock(String companyName, String ticketName) {
		this.companyName=companyName;
		this.tradingTicketName=ticketName;
		this.history=new LinkedList();
	}
	
	public Date getLastDate() throws Exception{
		if(this.history.size()==0) throw new Exception("investment.foundations.Stock class : getLastDate method : trying to obtain last stock values from a non charged stock");
		return ((StockDataUnit)this.history.getFirst()).getTimeSeal();	
	}
	
	public void addDataUnit(StockDataUnit unit) {
		this.history.add(unit);
	}
}
