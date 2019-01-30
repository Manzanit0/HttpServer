package core;

import core.models.Headers;
import core.models.Request;
import core.models.Response;

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

    protected Headers getDefaultHeaders() {
        return new Headers();
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

        Headers defaultHeaders = getDefaultHeaders();
        return res.withHeaders(defaultHeaders);
    }
}