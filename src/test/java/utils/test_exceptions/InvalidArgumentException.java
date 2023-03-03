package utils.test_exceptions;

import utils.TestTracker;

public class InvalidArgumentException extends RuntimeException{

    public InvalidArgumentException(String message) {
        super(message);
        System.out.println(String.format("Test Step: %s", TestTracker.get().getTestStep()));
        System.out.println(this);
    }
    public InvalidArgumentException(String message, Throwable cause) {
        super(message, cause);
        System.out.println(String.format("Test Step: %s", TestTracker.get().getTestStep()));
        System.out.println(this);
    }
}

