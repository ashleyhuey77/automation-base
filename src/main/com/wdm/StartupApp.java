package com.wdm;

public class StartupApp {

    public static void main(String[] args) throws Exception {
        new WebDriverManager().chromedriver().setup();
    }
}
