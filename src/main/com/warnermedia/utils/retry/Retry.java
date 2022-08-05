package com.warnermedia.utils.retry;

import com.warnermedia.config.TestException;
import com.warnermedia.utils.ex.FileDownloadException;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public final class Retry<T> implements TestOperation<T> {
    private static final Random RANDOM = new Random();
    private final TestOperation<T> op;
    private final int maxAttempts;
    private final long maxDelay;
    private final AtomicInteger attempts;
    private final Predicate<Exception> test;
    private final List<Exception> errors;

    @SafeVarargs
    public Retry(TestOperation<T> op,
                 int maxAttempts,
                 long maxDelay,
                 Predicate<Exception>... ignoreTests) {
        this.op = op;
        this.maxAttempts = maxAttempts + 1;
        this.maxDelay = maxDelay;
        this.attempts = new AtomicInteger();
        this.test = Arrays.stream(ignoreTests).reduce(Predicate::or).orElse(e -> false);
        this.errors = new ArrayList<>();

    }

    /**
     * The errors encountered while retrying, in the encounter order.
     *
     * @return the errors encountered while retrying
     */
    public List<Exception> errors() {
        return Collections.unmodifiableList(this.errors);
    }

    /**
     * The number of retries performed.
     *
     * @return the number of retries performed
     */
    public int attempts() {
        return this.attempts.intValue();
    }

    @Override
    public T perform() throws TestException {
        do {
            try {
                return this.op.perform();
            } catch (Exception e) {
                this.errors.add(e);

                if (this.attempts.incrementAndGet() >= this.maxAttempts
                        || !this.test.test(e)) {
                    throw e;
                }

                try {
                    Thread.sleep(this.maxDelay);
                } catch (InterruptedException f) {
                    //ignore
                }
            }
        } while (true);
    }
}
