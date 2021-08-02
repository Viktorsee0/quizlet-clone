package by.tms.quizletclone.util;

public class ImageUploadException extends RuntimeException {

    public ImageUploadException() {
    }

    public ImageUploadException(String message) {
        super(message);
    }

    public ImageUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageUploadException(Throwable cause) {
        super(cause);
    }
}
