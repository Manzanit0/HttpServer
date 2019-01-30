package application;

import core.Endpoint;
import core.models.Request;
import core.models.Response;
import core.models.ResponseHeader;

import java.util.Map;

import static core.models.ResponseHeader.*;

public class MethodOptions2 extends Endpoint {
    public MethodOptions2() {
    }

    @Override
    public String getUri() {
        return "/method_options2";
    }

    @Override
    protected Map<ResponseHeader, String> getDefaultHeaders() {
        return Map.of(ALLOW, "GET,HEAD,OPTIONS,PUT,POST");
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
