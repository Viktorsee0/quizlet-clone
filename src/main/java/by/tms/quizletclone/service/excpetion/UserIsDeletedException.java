package by.tms.quizletclone.service.excpetion;

public class UserIsDeletedException extends RuntimeException {
    public UserIsDeletedException() {
    }

    public UserIsDeletedException(String message) {
        super(message);
    }

    public UserIsDeletedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIsDeletedException(Throwable cause) {
        super(cause);
    }

    public UserIsDeletedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
