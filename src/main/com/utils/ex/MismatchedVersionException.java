package com.utils.ex;

import com.config.TestException;

public class MismatchedVersionException extends TestException {
    private static final long serialVersionUID = -8460356990632230194L;

    private final ErrorCode code;

    public MismatchedVersionException(ErrorCode code) {
        super(code.getDescription());
        this.code = code;
    }

    public MismatchedVersionException(String message, ErrorCode code) {
        super(code.getDescription() + "\n" + message);
        this.code = code;
    }

    public ErrorCode getCode() {
        return this.code;
    }

}