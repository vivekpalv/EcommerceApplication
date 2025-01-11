package com.ecom.amazon.CustomException;

public class VendorException extends RuntimeException{

    public VendorException(String message) {
        super(message);
    }

    public VendorException(Throwable cause) {
        super(cause);
    }

    public VendorException(String message, Throwable cause) {
        super(message, cause);
    }

    public VendorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
