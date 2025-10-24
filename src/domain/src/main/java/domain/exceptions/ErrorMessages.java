package domain.exceptions;

public enum ErrorMessages {
    SAMPLE_NOT_FOUND("Sample with ID %s was not found");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String formatted(Object... args) {
        return String.format(message, args);
    }
}
