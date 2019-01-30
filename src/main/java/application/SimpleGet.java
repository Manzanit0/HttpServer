package application;

import core.Endpoint;
import core.messages.Request;
import core.messages.Response;

public class SimpleGet extends Endpoint {
    public String getUri() {
        return "/simple_get";
    }

    @Override
    public Response get(Request request) {
        return Response.ok();
    }

    @Override
    public Response head(Request request) {
        return Response.ok();
    }
}
