package investment.foundations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import investment.foundations.YahooFinancialLoader;
import libs.cache.Cache;
import libs.cache.CacheLoaderITF;
import libs.cache.CachePersistenceITF;
import libs.cache.FakeCachePersistor;
import libs.utils.Trace;

class TestYahooFinancialLoader {

	@Test
	void test() {		
		if(Trace.TRACE) Trace.tracing(null, this, "Yahoo Financial Loader Test : INITIATING");		
		try {
			CacheLoaderITF loader=new YahooFinancialLoader();
			if(Trace.VERBOSE_TRACE) Trace.tracing(null, this, "Yahoo Financial Loader Created");		
			String[] values={"BABA","GOOG"};
			loader.initialStocksList(values);
			CachePersistenceITF persistor = new FakeCachePersistor();
			if(Trace.VERBOSE_TRACE) Trace.tracing(null, this, "Values to fetch added : BABA , GOOG");		
			Cache cache=new Cache();
			cache.setLoader(loader);
			cache.setPersistor(persistor);
			cache.activate();
			if(Trace.VERBOSE_TRACE) Trace.tracing(null, this, "Cache loaded with initial values");		
			assert(cache.lenght()==3);
			if(Trace.VERBOSE_TRACE) Trace.tracing(null, this, "Cache size test : OK");		
			assert(cache.get("BABA")!=null);
			assert(cache.get("GOOG")!=null);
			if(Trace.VERBOSE_TRACE) Trace.tracing(null, this, "Cache get test : OK");		
			assert(cache.retrieve("BABA")!=null);
			if(Trace.VERBOSE_TRACE) Trace.tracing(null, this, "Cache retrieve test : OK");		
			assert(cache.get("INTL")==null);
			assert(cache.retrieve("INTL")!=null);
			assert(cache.lenght()==4);
			if(Trace.VERBOSE_TRACE) Trace.tracing(null, this, "Cache size test post retrieve : OK");		
		}catch(Exception e) {
			fail(e.getMessage());
		}
		if(Trace.VERBOSE_TRACE) Trace.tracing(null, this, "Yahoo Financial Loader Test : OK");		
	}

}
