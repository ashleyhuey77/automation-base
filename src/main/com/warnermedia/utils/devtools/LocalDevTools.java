package com.warnermedia.utils.devtools;

import org.openqa.selenium.devtools.DevTools;

public class LocalDevTools {

    private static ThreadLocal<DevTools> THREAD_LOCAL = new ThreadLocal();
    private static Object mutex = new Object();

    public static DevTools getDevTools() {
        DevTools localRef = THREAD_LOCAL.get();
        try {
            if (localRef == null) {
                synchronized (mutex) {
                    THREAD_LOCAL.set(localRef);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return localRef;
    }

    public static void setDevTools(DevTools tools) {
        THREAD_LOCAL.set(tools);
    }
}
