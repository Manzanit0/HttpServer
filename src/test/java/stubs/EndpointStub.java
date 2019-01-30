package stubs;

import core.Endpoint;
import core.messages.Headers;
import core.messages.Request;
import core.messages.Response;

public class EndpointStub extends Endpoint {
    public EndpointStub() {
    }

    @Override
    protected Headers getDefaultHeaders() {
        Headers headers = new Headers();
        headers.add("Allow", "GET");
        return headers;
    }

    public String getUri() {
        return "/false_endpoint";
    }

    public Response get(Request request) {
        return Response.ok();
    }
}
