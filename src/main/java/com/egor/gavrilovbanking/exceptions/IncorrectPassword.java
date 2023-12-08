package com.egor.gavrilovbanking.exceptions;

public class IncorrectPassword extends Exception {
    public IncorrectPassword() {
        super();
    }

    public IncorrectPassword(String message) {
        super(message);
    }

    public IncorrectPassword(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectPassword(Throwable cause) {
        super(cause);
    }

    protected IncorrectPassword(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
