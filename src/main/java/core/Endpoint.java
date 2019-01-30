package core;

import core.models.Request;
import core.models.Response;
import core.models.ResponseHeader;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Endpoint {
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

    protected Map<ResponseHeader, String> getDefaultHeaders() {
        return new LinkedHashMap<>();
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

        Map<ResponseHeader, String> defaultHeaders = getDefaultHeaders();
        return res.withHeaders(defaultHeaders);
    }
}