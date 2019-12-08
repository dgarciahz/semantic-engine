package libs.cache;

import java.io.IOException;
import java.util.HashMap;

public class FakeCachePersistor implements CachePersistenceITF {

	public void persist(HashMap<String, Object> cache) throws IOException {}

	public HashMap<String, Object> loadFromPersistence() throws IOException {
		HashMap<String, Object> cache=new HashMap<String, Object>();
		cache.put("1", "value1");
		return cache;
	}

	public void purge() throws IOException {	}

	public void setFullFileName(String fileName) {}
}
