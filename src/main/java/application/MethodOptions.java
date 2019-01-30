package application;

import core.Endpoint;
import core.models.Request;
import core.models.Response;
import core.models.ResponseHeader;

import java.util.Map;

import static core.models.ResponseHeader.*;

public class MethodOptions extends Endpoint {
    public MethodOptions() {
    }

    public String getUri() {
        return "/method_options";
    }

    @Override
    protected Map<ResponseHeader, String> getDefaultHeaders() {
        return Map.of(ALLOW, "GET,HEAD,OPTIONS");
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