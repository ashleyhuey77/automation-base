package com.warnermedia.utils.devtools.filter;

import org.openqa.selenium.JavascriptException;

public interface Filter {
    /**
     * Execute order processing filter.
     */
    void execute(JavascriptException exception) throws Exception;

    /**
     * Set next filter in chain after this.
     */
    void setNext(Filter filter);

    /**
     * Get next filter in chain after this.
     */
    Filter getNext();

    /**
     * Get last filter in the chain.
     */
    Filter getLast();
}
