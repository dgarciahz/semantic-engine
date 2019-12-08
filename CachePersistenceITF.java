package libs.cache;

import java.io.IOException;
import java.util.*;


public interface CachePersistenceITF {
	public void persist(HashMap<String,Object> cache)  throws IOException;
	public HashMap<String,Object>  loadFromPersistence() throws IOException;
	public void purge()  throws IOException;	
	public void setFullFileName(String fileName);
}
