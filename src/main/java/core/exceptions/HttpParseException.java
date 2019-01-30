package core.exceptions;

/**
 * Thrown when an the HTTP request can't be succesfully parsed.
 * For example, a request without HTTP version throws an
 * instance of this class.
 */
public class HttpParseException extends Exception {
    public HttpParseException(Exception ex) {
        super(ex);
    }

    public HttpParseException(String message) {
        super(message);
    }
}
