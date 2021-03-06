package core;

import core.models.Request;
import core.models.Response;
import core.models.ResponseHeader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Endpoint {
    private List<String> allowedMethods = new ArrayList<>();

    public abstract String getUri();

    protected Response post(Request request) {
        return Response.notAllowed();
    }

    protected Response get(Request request) {
        return Response.notAllowed();
    }

    protected Response put(Request request) {
        return Response.notAllowed();
    }

    protected Response head(Request request) {
        return Response.notAllowed();
    }

    protected Response options(Request request) {
        return Response.notAllowed();
    }

    public Response getResponse(Request request) {
        Response res;

        switch (request.getMethod()) {
            case "POST":
                res = post(request);
                break;
            case "GET":
                res = get(request);
                break;
            case "PUT":
                res = put(request);
                break;
            case "HEAD":
                res = head(request);
                break;
            case "OPTIONS":
                res = options(request);
                break;
            default:
                res = Response.badRequest();
        }

        return res.withHeaders(getDefaultHeaders());
    }

    private Map<ResponseHeader, String> getDefaultHeaders() {
        Map<ResponseHeader, String> defaultHeaders = new HashMap<>();

        defaultHeaders.put(ResponseHeader.SERVER, "Javier's awesome server");

        if (!allowedMethods.isEmpty()) {
            defaultHeaders.put(ResponseHeader.ALLOW, String.join(",", allowedMethods));
        }

        return defaultHeaders;
    }

    protected void setAllowedMethodsHeader(List<String> allowedMethods) {
        this.allowedMethods = allowedMethods;
    }
}