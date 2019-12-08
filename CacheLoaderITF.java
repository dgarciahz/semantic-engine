package libs.cache;

import java.io.IOException;

public interface CacheLoaderITF {
	public void initialStocksList(String[] values) ;
	public void initialLoad(Cache cache)  throws IOException;
	public Object retrieve(String key,Cache cache) throws IOException;
}
