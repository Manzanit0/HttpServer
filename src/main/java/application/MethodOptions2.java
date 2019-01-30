package application;

import core.Endpoint;
import core.models.Headers;
import core.models.Request;
import core.models.Response;

public class MethodOptions2 extends Endpoint {
    public MethodOptions2() {
    }

    @Override
    public String getUri() {
        return "/method_options2";
    }

    @Override
    protected Headers getDefaultHeaders() {
        Headers headers = new Headers();
        headers.add("ALLOW", "GET,HEAD,OPTIONS,PUT,POST");
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

    @Override
    protected Response put(Request request) {
        return Response.ok();
    }

    @Override
    protected Response post(Request request) {
        return Response.ok();
    }
}
