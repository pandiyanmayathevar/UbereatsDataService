package io.swagger.exception;

import io.swagger.utils.MessageUtils;

import java.util.Arrays;

/**
 * This class represents the case when there's not data found for a given parameter. 
 *
 */
public class NoDataFoundException extends AppRuntimeException {


    public NoDataFoundException() {
        super();
    }

    public NoDataFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoDataFoundException(String message) {
        super(message);
    }

    public NoDataFoundException(Throwable cause) {
        super(cause);
    }
    @Override
   	public String toString() {
   		return "NoDataFoundException [ getErrorMap()=" + MessageUtils.printMapInJSONFormat(getErrorMap())
   				+ ", getMessage()="
   				+ getMessage() + ", getCause()=" + getCause() + ", getStackTrace()=" + Arrays.toString(getStackTrace())
   				+ "]";
   	}
}