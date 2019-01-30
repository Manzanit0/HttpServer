package core.models;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Response {
    private static final String HTTP_VERSION = "HTTP/1.1";

    private ResponseType type;
    private Map<ResponseHeader, String> headers;
    private String body;

    public Response(ResponseType type) {
        this.type = type;
        this.body = "";
        this.headers = new LinkedHashMap<>();
        headers.putAll(Map.of(ResponseHeader.SERVER, "Javier's awesome server"));
    }

    public static Response notFound() {
        return new Response(ResponseType.NOT_FOUND);
    }

    public static Response ok() {
        return new Response(ResponseType.OK);
    }

    public static Response redirect() {
        return new Response(ResponseType.REDIRECT);
    }

    public static Response notAllowed() {
        return new Response(ResponseType.NOT_ALLOWED);
    }

    public static Response badRequest() {
        return new Response(ResponseType.BAD_REQUEST);
    }

    public static Response internalError() {
        return new Response(ResponseType.INTERNAL_SERVER_ERROR);
    }

    public Response withBody(String body) {
        this.body = body;
        return this;
    }

    public Response withHeaders(Map<ResponseHeader, String> headers) {
        this.headers.putAll(headers);
        return this;
    }

    public Response withHeader(ResponseHeader key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public String getHttpVersion() {
        return HTTP_VERSION;
    }

    public int getStatusCode() {
        return type.getCode();
    }

    public String getReason() {
        return type.getReason();
    }

    public Map<ResponseHeader, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return getHttpVersion() + " " + getStatusCode() + " " + getReason() + "\n"
                + getFormattedHeaders()
                + getFormattedBody();
    }

    private String getFormattedHeaders() {
        if (getHeaders().size() == 0) return "";

        StringBuilder formattedHeaders = new StringBuilder();

        for (Entry<ResponseHeader, String> entry : getHeaders().entrySet()) {
            formattedHeaders.append(
                    String.format("%s: %s%n", entry.getKey().getValue(), entry.getValue()));
        }

        return formattedHeaders.toString();
    }

    private String getFormattedBody() {
        return getBody().isEmpty() ? "" : "\n" + getBody().trim();
    }
}
