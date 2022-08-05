package com.warnermedia.utils.ex;

import com.warnermedia.config.TestException;

public class ChromeDriverException extends TestException {
    private static final long serialVersionUID = -8460356990632230194L;

    private final ErrorCode code;

    public ChromeDriverException(ErrorCode code) {
        super(code.getDescription());
        this.code = code;
    }

    public ChromeDriverException(String message, ErrorCode code) {
        super(code.getDescription() + "\n" + message);
        this.code = code;
    }

    public ErrorCode getCode() {
        return this.code;
    }
}
