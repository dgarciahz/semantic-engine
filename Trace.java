package libs.utils;

import java.time.Instant;

public class Trace {
	public static final boolean CORE_TRACE=true;
	public static final boolean TRACE=true;
	public static final boolean VERBOSE_TRACE=true;
	
	private static final String HEADER="TRACE module";
	private static final String SEPARATOR=" : ";
	
	private Trace() {}
	
	public static void tracing(Exception e, Object obj, String message) {
		Instant timestampMark=Instant.now();
		StringBuffer txt=new StringBuffer(HEADER+SEPARATOR+timestampMark.getEpochSecond()+SEPARATOR+obj.getClass().getName()+SEPARATOR+message);
		System.out.println(txt);
		if(e!=null) {
			System.err.println(txt);
			e.printStackTrace();
		}
	}
}
