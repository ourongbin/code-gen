package io.github.ourongbin.dev.codegen.util;

public class CodeGenException extends RuntimeException {

    private static final long serialVersionUID = 42L;

    public CodeGenException() {
        super();
    }

    public CodeGenException(String msg) {
        super(msg);
    }

    public CodeGenException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CodeGenException(Throwable cause) {
        super(cause);
    }

    public CodeGenException(String message, Throwable cause,
                            boolean enableSuppression,
                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
