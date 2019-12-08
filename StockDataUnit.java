package investment.foundations;

import java.math.BigDecimal;
import java.util.Date;

public class StockDataUnit{

	private Date timeSeal=null;
	
	public Date getTimeSeal() {
		return timeSeal;
	}
	public void setTimeSeal(Date timeSeal) {
		this.timeSeal = timeSeal;
	}
	private BigDecimal openValue=null;
	private BigDecimal Ask=null;
	private BigDecimal Bid=null;

	private BigDecimal numberOfShares=null;
	private BigDecimal volume=null;
	
	private BigDecimal beta=null;
	
	//The price to earnings ratio reflects the relationship between the price per share and the income earned per share
	private BigDecimal PER=null;
	
	//Earnings per share is the amount of money that you would have earned if you purchased a share of this stock last quarter and sold it today.
	private BigDecimal EPS=null;
	
	private BigDecimal VaR=null;
	private BigDecimal desviaciónTípica=null;

	public BigDecimal getOpenValue() {
		return openValue;
	}
	public void setOpenValue(BigDecimal value) {
		openValue = value;
	}
	public BigDecimal getAsk() {
		return Ask;
	}
	public void setAsk(BigDecimal ask) {
		Ask = ask;
	}
	public BigDecimal getBid() {
		return Bid;
	}
	public void setBid(BigDecimal bid) {
		Bid = bid;
	}
	public BigDecimal getNumberOfShares() {
		return numberOfShares;
	}
	public void setNumberOfShares(BigDecimal numberOfShares) {
		this.numberOfShares = numberOfShares;
	}
	public BigDecimal getVolume() {
		return volume;
	}
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	public BigDecimal getBeta() {
		return beta;
	}
	public void setBeta(BigDecimal beta) {
		this.beta = beta;
	}
	public BigDecimal getPER() {
		return PER;
	}
	public void setPER(BigDecimal pER) {
		PER = pER;
	}
	public BigDecimal getEPS() {
		return EPS;
	}
	public void setEPS(BigDecimal ePS) {
		EPS = ePS;
	}
	public BigDecimal getVaR() {
		return VaR;
	}
	public void setVaR(BigDecimal vaR) {
		VaR = vaR;
	}
	public BigDecimal getDesviaciónTípica() {
		return desviaciónTípica;
	}
	public void setDesviaciónTípica(BigDecimal desviaciónTípica) {
		this.desviaciónTípica = desviaciónTípica;
	}
}
