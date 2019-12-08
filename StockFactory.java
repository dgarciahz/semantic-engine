package investment.foundations;

import java.io.IOException;
import libs.cache.Cache;
import libs.cache.CacheLoaderITF;
import libs.cache.CachePersistenceITF;
import investment.foundations.Stock;

public class StockFactory {
	private static Cache cache=null;
	private static StockFactory singleton=null;
	public static StockFactory getStockFactory() throws Exception{
		if (singleton!=null) singleton=new StockFactory();		
		return singleton;
	}
	private StockFactory() throws Exception{
			this.cache=new Cache();

			CacheLoaderITF yahooStocksLoader=new YahooFinancialLoader();
			cache.setLoader(yahooStocksLoader);

			CachePersistenceITF persistor=new JSONLocalPersistence();
			persistor.setFullFileName(Parameters.FILE_JSON_PERSISTENCE);
			cache.setPersistor(persistor);

			cache.activate();
		}

	public Stock getStock(String ticketName) throws IOException  {
		return (Stock) this.cache.retrieve(ticketName);
	}	
}
