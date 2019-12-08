package libs.cache;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import libs.utils.Trace;

class TestCache {

	@Test
	void test() {
		try {
			if(Trace.TRACE) Trace.tracing(null, this, "Cache Test : INITIATING");					
			CacheLoaderITF loader=new FakeCacheLoader();
			String[] values= {"2"};
			loader.initialStocksList(values);
			CachePersistenceITF persistor=new FakeCachePersistor();
			Cache cache=new Cache();
			cache.setLoader(loader);
			cache.setPersistor(persistor);
			cache.activate();
			
			if(Trace.VERBOSE_TRACE) Trace.tracing(null, this, "Cache size : "+cache.lenght());
			assert(cache.lenght()==2);
			
			cache.retrieve("3");
			cache.retrieve("2");
			cache.retrieve("3");
			if(Trace.VERBOSE_TRACE) Trace.tracing(null, this, "Cache size : "+cache.lenght());
			assert(cache.lenght()==3);

			assert(((String)cache.retrieve("2")).equals("value2"));
			if(Trace.TRACE) Trace.tracing(null, this, "Cache Test : OK");		
		}catch(Exception e) {
			fail("Exception : "+e.getMessage());
		}
	}

}
