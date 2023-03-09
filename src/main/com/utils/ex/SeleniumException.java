package com.utils.ex;

import com.config.TestException;

public class SeleniumException extends TestException {
    private static final long serialVersionUID = -8460356990632230194L;

    private final ErrorCode code;

    public SeleniumException(ErrorCode code) {
        super(code.getDescription());
        this.code = code;
    }

    public SeleniumException(String message, ErrorCode code) {
        super("Error Code " + ErrorCode.FIND_ELEMENT.getCode() + ": " + code.getDescription() + "\n" + message);
        this.code = code;
    }

    public ErrorCode getCode() {
        return this.code;
    }
}
