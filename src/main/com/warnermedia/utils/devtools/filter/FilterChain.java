package com.warnermedia.utils.devtools.filter;

import org.openqa.selenium.JavascriptException;

public class FilterChain {

    private Filter chain;


    /**
     * Adds filter.
     */
    public void addFilter(Filter filter) {
        if (chain == null) {
            chain = filter;
        } else {
            chain.getLast().setNext(filter);
        }
    }

    /**
     * Execute filter chain.
     */
    public void execute(JavascriptException exception) throws Exception {
        if (chain != null) {
            chain.execute(exception);
        }
    }
}
