package investment.foundations;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import libs.cache.CachePersistenceITF;
import libs.utils.Trace;

// crear la cache local???

public class JSONLocalPersistence implements CachePersistenceITF {
	
	public void persist(HashMap<String,Object> cache) throws IOException {
		if(Trace.VERBOSE_TRACE)Trace.tracing(null, this, "persist : JSON file "+this.persistenceFileName);
		if(this.persistenceFileName==null) throw new IOException("JSONLocalPersistence : persist : no LocalFileName had been defined");
		//configure Object mapper for pretty print
		ObjectMapper objectMapper = new ObjectMapper();
		if(Trace.VERBOSE_TRACE)Trace.tracing(null, this, "persist : proceeding to serialize");
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		if(Trace.VERBOSE_TRACE)Trace.tracing(null, this, "persist : serialization completed");
		//writing to console, can write to any output stream such as file
		StringWriter stringCache = new StringWriter();
		objectMapper.writeValue(stringCache, cache);

		FileWriter archivo =null;
		try {
			archivo = new FileWriter(this.persistenceFileName);
			archivo.write(stringCache.toString());
		}catch(IOException e) {throw e;}
		finally {archivo.close();}
		if(Trace.VERBOSE_TRACE)Trace.tracing(null, this, "persist : completed");
	}

	public HashMap<String,Object> loadFromPersistence() throws IOException {
		//read json file data to String
		if(this.persistenceFileName==null) throw new IOException("JSONLocalPersistence : persist : no LocalFileName had been defined");
		File archivo = new File(this.persistenceFileName);
		if (archivo.exists()) {
			if(Trace.TRACE)Trace.tracing(null, this, "loadFromPersistence : JSON file "+this.persistenceFileName+" located");
			byte[] jsonData = Files.readAllBytes(Paths.get(this.persistenceFileName));
			if(Trace.VERBOSE_TRACE)Trace.tracing(null, this, "loadFromPersistence : data read");
			//create ObjectMapper instance
			ObjectMapper objectMapper = new ObjectMapper();
			//convert json string to object
			HashMap<String,Object> cache = objectMapper.readValue(jsonData, HashMap.class);		
			if(Trace.VERBOSE_TRACE)Trace.tracing(null, this, "loadFromPersistence : data converted into cache");
			if(Trace.VERBOSE_TRACE)Trace.tracing(null, this, "loadFromPersistence : cache load from JSON size : "+cache.size());	
			return cache;
		}
		if(Trace.TRACE)Trace.tracing(null, this, "loadFromPersistence : the JSON file does not exist "+this.persistenceFileName);
		return null;
	}

	public void purge()  throws IOException{
		if(this.persistenceFileName==null) throw new IOException("JSONLocalPersistence : purge : no LocalFileName had been defined");		
		Files.deleteIfExists(Paths.get(this.persistenceFileName));
	}

	String persistenceFileName=null;
	public void setFullFileName(String fileName) {
		this.persistenceFileName=fileName;
	}
}
