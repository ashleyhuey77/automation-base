package com.utils.devtools.filter;

import org.openqa.selenium.JavascriptException;

public class FilterManager {
    private final FilterChain filterChain;

    public FilterManager() {
        filterChain = new FilterChain();
    }

    public void addFilter(Filter filter) {
        filterChain.addFilter(filter);
    }

    public void filterRequest(JavascriptException exception) throws Exception {
        filterChain.execute(exception);
    }
}
