package libs.cache;

import java.io.IOException;
import java.util.*;

import investment.foundations.YahooFinancialLoader;
import libs.utils.Trace;

public class Cache {
	private static final float INITIAL_CACHE_THRESSHOLD=0.9f;
	private static final int INITIAL_CACHE_SIZE=200;
	
	private HashMap<String, Object> cache=null;
	private CacheLoaderITF loader=null;
	private CachePersistenceITF persistor=null;
	/**
	 * 
	 * @param loader data loader for this concrete cache ocurrence. This loader will be call initially, and later if necessary to recover a value not present.
	 * @throws Exception if the loader is null, then it is not possible to proceed
	 */
//	public Cache(CacheLoaderITF loader,CachePersistenceITF persistor) throws IOException{
	
	public Cache() {
		
	}
	public void setLoader(CacheLoaderITF loader) {
		this.loader=loader;
	}
	public void setPersistor(CachePersistenceITF persistor) {
		this.persistor=persistor;
	}
	public void activate() throws Exception {	
		if(Trace.TRACE)Trace.tracing(null, this, "Cache : Chace is going to be created");
		IOException exp=null;
		if(loader==null) exp=new IOException(this.getClass().getName()+" need Loader not null on creation");
		if(persistor==null) exp=new IOException(this.getClass().getName()+" need Persistor not null on creation");
		if(exp!=null) {
			if(Trace.CORE_TRACE) Trace.tracing(exp, this, "Cache : Error with IoC when creating a new cache");
			throw exp;
		}
		
		// nos traemos del JSON lo que haya
		if(Trace.VERBOSE_TRACE)Trace.tracing(null, this, "Cache : Se va a cargar del almacenamiento JSON");
		HashMap<String, Object> cacheCopy=this.persistor.loadFromPersistence();
		if(cacheCopy!=null)  this.cache=cacheCopy;
		else this.cache=new HashMap<String, Object>(INITIAL_CACHE_SIZE,INITIAL_CACHE_THRESSHOLD);
		if(Trace.VERBOSE_TRACE)Trace.tracing(null, this, "Cache : Cargado del almacenamiento JSON");
		if(Trace.VERBOSE_TRACE)Trace.tracing(null, this, "Cache : size : "+this.lenght());
		
		// lo reforzamos con los updates
		if(Trace.VERBOSE_TRACE)Trace.tracing(null, this, "Cache : Se va cargar del origen remoto de datos");
		this.loader.initialLoad(this);
		if(Trace.VERBOSE_TRACE)Trace.tracing(null, this, "Cache : Cargado del origen remoto de datos");
		
		// lo volvemos a guardar para la proxima vez
		if(Trace.VERBOSE_TRACE)Trace.tracing(null, this, "Cache : Se va a almacenar en JSON una version extendida");
		this.persistor.persist(this.cache);
		if(Trace.VERBOSE_TRACE)Trace.tracing(null, this, "Cache : Se ha almacenado en JSON la version extendida");
		
		if(Trace.CORE_TRACE)Trace.tracing(null, this, "Cache : Cache creation:OK");		
	}

/**
	 * @summary this method returns the value associated with the key from the cache, or from the loader if not present at the cache. In the latter, it will add the new ocurrence to the cache
	 * @param key is the key of the ocurrence to retrieve
	 * @return the object stored associated with the key, of null if none. 
	 * @throws IOException when there is an IOException from the loader
	 */
	public Object retrieve(String key) throws IOException {
		if(Trace.VERBOSE_TRACE)Trace.tracing(null, this, "retrieve : Recuperando valor de la cache : "+key);
		if (this.cache.get(key)!=null) return this.cache.get(key);
		synchronized(this.cache) {
			if (this.cache.get(key)!=null) return this.cache.get(key);
			if(Trace.TRACE)Trace.tracing(null, this, "retrieve : Buscando valor para insertarlo en la cache con el loader: "+key);

			Object obj=this.loader.retrieve(key,this); //here we pass a reference to the cache, so that, the loader will be able to load the value retrieve if any
			if(Trace.TRACE)Trace.tracing(null, this, "retrieve : Recuperado valor OK e insertado en la cache con el loader: "+key);
			
			return obj;
		}
	}

	public Object get(String key) {
		if(Trace.VERBOSE_TRACE) Trace.tracing(null, this, "get : Getting value from cache : "+key);
		return this.cache.get(key);
	}
	
	/**
	 * @summary this method adds the value, overwrittting any possible prior ocurrence
	 * @param key
	 * @param value
	 */
	public void add(String key,Object value) {
		synchronized(this.cache) { 
			if(Trace.VERBOSE_TRACE) Trace.tracing(null, this, "add : Adding value to the cache : "+key);
			this.cache.put(key, value);
			if(Trace.VERBOSE_TRACE) Trace.tracing(null, this, "add : cache size : "+this.lenght());
		}
	}

	public int lenght() {
		return this.cache.size();
	}

	public void purge() {
		this.cache=new HashMap<String, Object>(INITIAL_CACHE_SIZE,INITIAL_CACHE_THRESSHOLD);
	}
	
	public void forcePersist() throws IOException{
		this.persistor.persist(this.cache);
	}

	public HashMap<String, Object> getCache(){
		return this.cache;
	}
	
	public void setCache(HashMap<String, Object> cache) {
		if(Trace.VERBOSE_TRACE) Trace.tracing(null, this, "Setting cache");		
		this.cache=cache;
		if(Trace.VERBOSE_TRACE) Trace.tracing(null, this, "Cache size : "+this.lenght());		
	}

}
