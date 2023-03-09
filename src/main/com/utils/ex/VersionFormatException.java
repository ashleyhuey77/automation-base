package com.utils.ex;

import com.config.TestException;

public class VersionFormatException extends TestException {
    private static final long serialVersionUID = -8460356990632230194L;

    private final ErrorCode code;

    public VersionFormatException(ErrorCode code) {
        super(code.getDescription());
        this.code = code;
    }

    public VersionFormatException(String message, ErrorCode code) {
        super(code.getDescription() + "\n" + message);
        this.code = code;
    }

    public ErrorCode getCode() {
        return this.code;
    }

}
