package com.utils.devtools.filter;

import org.openqa.selenium.JavascriptException;

public abstract class AbstractFilter implements Filter {


    private Filter next;

    public AbstractFilter() {
    }

    public AbstractFilter(Filter next) {
        this.next = next;
    }

    @Override
    public void setNext(Filter filter) {
        this.next = filter;
    }

    @Override
    public Filter getNext() {
        return next;
    }

    @Override
    public Filter getLast() {
        Filter last = this;
        while (last.getNext() != null) {
            last = last.getNext();
        }
        return last;
    }

    @Override
    public void execute(JavascriptException exception) throws Exception {
        if (getNext() != null) {
            getNext().execute(exception);
        }
    }
}
