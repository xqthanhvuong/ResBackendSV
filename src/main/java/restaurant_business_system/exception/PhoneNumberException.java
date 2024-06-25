package restaurant_business_system.exception;

public class PhoneNumberException extends RuntimeException {

    public PhoneNumberException() {
        super();
    }

    public PhoneNumberException(String message) {
        super(message);
    }

    public PhoneNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneNumberException(Throwable cause) {
        super(cause);
    }
}