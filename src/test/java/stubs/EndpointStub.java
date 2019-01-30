package stubs;

import core.Endpoint;
import core.models.Request;
import core.models.Response;
import core.models.ResponseHeader;

import java.util.Map;

import static core.models.ResponseHeader.ALLOW;

public class EndpointStub extends Endpoint {
    public EndpointStub() {
    }

    @Override
    protected Map<ResponseHeader, String> getDefaultHeaders() {
        return Map.of(ALLOW, "GET");
    }

    public String getUri() {
        return "/false_endpoint";
    }

    public Response get(Request request) {
        return Response.ok();
    }
}
