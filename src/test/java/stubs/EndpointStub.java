package stubs;

import core.Endpoint;
import core.messages.Headers;
import core.messages.Request;
import core.messages.Response;

public class EndpointStub extends Endpoint {
    public EndpointStub() {
    }

    public String getUri() {
        return "/false_endpoint";
    }

    @Override
    protected Headers getDefaultHeaders() {
        Headers headers = new Headers();
        headers.add("Allow", "GET");
        return headers;
    }

    @Override
    public Response get(Request request) {
        return Response.ok();
    }
}
