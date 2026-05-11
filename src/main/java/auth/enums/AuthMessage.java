package auth.enums;

public enum AuthMessage {
    SUCCESS_REGISTRATION("success register"),
    NOT_FOUND("not found"),
    NOT_RIGHT_PASS("not right pass"),
    SUCCESS_LOGOUT("success logout");

    private final String message;

    AuthMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
