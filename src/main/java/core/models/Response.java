package core.models;

public class Response {
    private static final String HTTP_VERSION = "HTTP/1.1";

    private ResponseType type;
    private Headers headers;
    private String body;

    public Response(ResponseType type) {
        this.type = type;
        this.body = "";

        this.headers = new Headers();
        this.headers.add("Server", "Javier's awesome server");
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

    public Response withHeaders(Headers headers) {
        this.headers.addAll(headers);
        return this;
    }

    public Response withHeader(Header header) {
        this.headers.add(header);
        return this;
    }

    public Response withHeader(String name, String value) {
        this.headers.add(name, value);
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

    public Headers getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return getHttpVersion() + " " + getStatusCode() + " " + getReason() + "\n"
                + headers.toString()
                + getFormattedBody();
    }

    private String getFormattedBody() {
        return getBody().isEmpty() ? "" : "\n" + getBody().trim();
    }
}
