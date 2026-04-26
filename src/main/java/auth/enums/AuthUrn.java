package auth.enums;

public enum AuthUrn {
    REGISTER("/register"),
    LOGIN("/login"),
    LOGOUT("/logout");

    private final String urn;

    AuthUrn(String urn) {
        this.urn = urn;
    }

    public String getUrn() {
        return urn;
    }
}
