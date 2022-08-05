package com.warnermedia.utils.retry;

import com.warnermedia.config.TestException;

public interface TestOperation<T> {

    T perform() throws TestException;
}
