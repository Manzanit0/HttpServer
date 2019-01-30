package application;

import core.Endpoint;
import core.models.Headers;
import core.models.Request;
import core.models.Response;

public class MethodOptions extends Endpoint {
    public MethodOptions() {
    }

    public String getUri() {
        return "/method_options";
    }

    @Override
    protected Headers getDefaultHeaders() {
        Headers headers = new Headers();
        headers.add("ALLOW", "GET,HEAD,OPTIONS");
        return headers;
    }

    @Override
    protected Response get(Request request) {
        return Response.ok();
    }

    @Override
    protected Response head(Request request) {
        return Response.ok();
    }

    @Override
    protected Response options(Request request) {
        return Response.ok();
    }
}