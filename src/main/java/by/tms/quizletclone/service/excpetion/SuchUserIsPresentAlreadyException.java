package by.tms.quizletclone.service.excpetion;

public class SuchUserIsPresentAlreadyException extends RuntimeException {
    public SuchUserIsPresentAlreadyException() {
    }

    public SuchUserIsPresentAlreadyException(String message) {
        super(message);
    }

    public SuchUserIsPresentAlreadyException(String message, Throwable cause) {
        super(message, cause);
    }

    public SuchUserIsPresentAlreadyException(Throwable cause) {
        super(cause);
    }

    public SuchUserIsPresentAlreadyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
