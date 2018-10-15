package log;

import java.util.logging.Logger;

public class Log {
	
	private Log() {
		
	}

	private static final ThreadLocal<Logger> l = new ThreadLocal<>();
	
	public static Logger get() {
		return l.get();
	}
	
	public static void set() {
		l.set(Logger.getLogger(Log.class.getName()));
	}
}
