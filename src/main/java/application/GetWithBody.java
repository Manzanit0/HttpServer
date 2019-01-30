package application;

import core.Endpoint;
import core.models.Headers;
import core.models.Request;
import core.models.Response;

public class GetWithBody extends Endpoint {
    public GetWithBody() {
    }

    public String getUri() {
        return "/get_with_body";
    }

    @Override
    protected Headers getDefaultHeaders() {
        Headers headers = new Headers();
        headers.add("ALLOW", "HEAD,OPTIONS");
        return headers;
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
