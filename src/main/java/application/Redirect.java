package application;

import core.Endpoint;
import core.messages.Request;
import core.messages.Response;

public class Redirect extends Endpoint {
    public String getUri() {
        return "/redirect";
    }

    @Override
    public Response get(Request request) {
        return Response.redirect()
                .withHeader("Location", "http://0.0.0.0:5000/simple_get");
    }
}