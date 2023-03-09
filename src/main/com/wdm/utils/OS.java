package com.wdm.utils;

public enum OS {

    LINUX,
    WINDOWS,
    MAC;

    public static OS getOperatingSystem()
    {
        // detecting the operating system using `os.name` System property
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return OS.WINDOWS;
        }
        else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            return OS.LINUX;
        }
        else if (os.contains("mac")) {
            return OS.MAC;
        }
        return null;
    }
}
