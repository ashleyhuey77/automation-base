package common.utils.managers;

import java.util.Set;
import org.openqa.selenium.Cookie;

public class CookieManager {
	
	private static ThreadLocal<Set<Cookie>> baseCookies = new ThreadLocal<>();
	
	public static Set<Cookie> getCookies() {
		return baseCookies.get();
	}
	
	public static void setCookies(Set<Cookie> value) {
		baseCookies.set(value);
	}

}
