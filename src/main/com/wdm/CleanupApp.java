package com.wdm;

public class CleanupApp {
    public static void main(String[] args) throws Exception {
        new WebDriverManager().teardown();
    }
}
