package core.messages;

public enum ResponseCode {
    OK(200, "OK"),
    REDIRECT(301, "REDIRECT"),
    BAD_REQUEST(400, "BAD REQUEST"),
    NOT_FOUND(404, "NOT FOUND"),
    NOT_ALLOWED(405, "NOT ALLOWED"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL SERVER ERROR");

    private int code;
    private String reason;

    ResponseCode(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public int getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }
}
