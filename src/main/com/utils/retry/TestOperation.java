package com.utils.retry;

import com.config.TestException;

public interface TestOperation<T> {

    T perform() throws TestException;
}
