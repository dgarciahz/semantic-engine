package libs.cache;

import java.io.IOException;

public class FakeCacheLoader implements CacheLoaderITF {
	
	String[] values=null;
	public void initialStocksList(String[] values) {
		this.values=values;
	}

	public void initialLoad(Cache cache) throws IOException {
		for(int i=0;i<values.length;i++) {
			cache.add(values[i], "value"+values[i]);
		}
	}

	public Object retrieve(String key, Cache cache) throws IOException {
		cache.add(key, "value"+key);
		return "value"+key;
	}
}
