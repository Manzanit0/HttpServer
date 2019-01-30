package core.messages;

public class Request {
    private RequestMethod method;
    private String uri;
    private String httpVersion;
    private Headers headers;
    private String body;

    public Request() {
    }

    public Request(RequestMethod method, String uri, String httpVersion) {
        this.method = method;
        this.uri = uri;
        this.httpVersion = httpVersion;
    }

    public Request withMethod(RequestMethod method) {
        this.method = method;
        return this;
    }

    public Request withUri(String uri) {
        this.uri = uri;
        return this;
    }

    public Request withHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
        return this;
    }

    public Request withBody(String body) {
        this.body = body;
        return this;
    }

    public Request withHeaders(Headers headers) {
        this.headers = headers;
        return this;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public Headers getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }


}
