package application;

import core.Endpoint;
import core.models.Request;
import core.models.Response;
import core.models.ResponseHeader;

import java.util.Map;

import static core.models.ResponseHeader.*;

public class GetWithBody extends Endpoint {
    public GetWithBody() {
    }

    public String getUri() {
        return "/get_with_body";
    }

    @Override
    protected Map<ResponseHeader, String> getDefaultHeaders() {
        return Map.of(ALLOW, "HEAD,OPTIONS");
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
