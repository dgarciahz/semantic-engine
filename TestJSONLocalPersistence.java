package investment.foundations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import libs.cache.Cache;
import libs.cache.CacheLoaderITF;
import libs.cache.CachePersistenceITF;
import libs.cache.FakeCacheLoader;
import libs.utils.Trace;

class TestJSONLocalPersistence {

	@Test
	void test() {
		if(Trace.TRACE) Trace.tracing(null, this, "JSON Local Persistence Test : INITIATING");		
		try {
			CachePersistenceITF persistor = new JSONLocalPersistence();
			persistor.setFullFileName(Parameters.FILE_JSON_PERSISTENCE);
			persistor.purge();
			
			CacheLoaderITF FCL =new FakeCacheLoader();
			String[] values={"1","2"};
			FCL.initialStocksList(values);

			if(Trace.VERBOSE_TRACE) Trace.tracing(null, this, "JSON Local Persistence : key1:value1 , key2:value2");		
//			Cache cache=new Cache(FCL,persistor);
			Cache cache=new Cache();
			cache.setLoader(FCL);
			cache.setPersistor(persistor);
			cache.activate();
			if(Trace.VERBOSE_TRACE) Trace.tracing(null, this, "Cache loaded with initial values");		
		//	assert(cache.getSize());
		//	if(Trace.VERBOSE_TRACE) Trace.tracing(null, this, "Cache size test : OK");		
			assert(cache.get("1")!=null);
			assert(cache.get("2")!=null);
			assert(cache.get("3")==null);
			assert(cache.retrieve("3")!=null);
			assert(cache.lenght()==3);
			cache.forcePersist();
			if(Trace.VERBOSE_TRACE) Trace.tracing(null, this, "Cache forzada a persistir JSON");		

			cache.purge();
			assert(cache.lenght()==0);
			if(Trace.VERBOSE_TRACE) Trace.tracing(null, this, "Cache vaciada");		
			
			String[] values2={"4"};
			FCL.initialStocksList(values2);
			if(Trace.VERBOSE_TRACE) Trace.tracing(null, this, "Nueva Cache preparada");		
//			Cache cache2=new Cache(FCL,persistor);
			Cache cache2=new Cache();
			cache2.setLoader(FCL);
			cache2.setPersistor(persistor);
			cache2.activate();
			if(Trace.VERBOSE_TRACE) Trace.tracing(null, this, "Nueva Cache creada");		

			assert(cache2.lenght()==4);
			assert(cache2.get("1")!=null);
			assert(cache2.get("2")!=null);
			assert(cache2.get("3")!=null);
			if(Trace.VERBOSE_TRACE) Trace.tracing(null, this, "Cache post persistencia valores recuperados JSON : OK");		
			assert(cache2.get("4")!=null);
			if(Trace.VERBOSE_TRACE) Trace.tracing(null, this, "Cache post persistencia valores loaded : OK");		
		}catch(Exception e) {
			fail(e.getMessage());
		}
		if(Trace.VERBOSE_TRACE) Trace.tracing(null, this, "JSON Local Persistence Test : OK");		
	}

}
