package application;

import core.Endpoint;
import core.messages.Request;
import core.messages.Response;

public class EchoBody extends Endpoint {
    @Override
    public String getUri() {
        return "/echo_body";
    }

    @Override
    protected Response post(Request request) {
        return Response.ok()
                .withBody(request.getBody());
    }
}
