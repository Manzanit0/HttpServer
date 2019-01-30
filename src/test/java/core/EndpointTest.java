package core;

import core.models.Request;
import core.models.Response;
import org.junit.Test;
import stubs.EndpointStub;

import static org.junit.Assert.assertEquals;

public class EndpointTest {
    @Test
    public void getsCorrectResponseForExistingMethod() {
        Endpoint endpoint = new EndpointStub();
        Request request = new Request("GET", "/false_endpoint", "HTTP/1.1");

        Response response = endpoint.getResponse(request);

        assertEquals(200, response.getStatusCode());
        assertEquals("OK", response.getReason());
        assertEquals("GET", response.getHeaders().get("Allow").getValue());
    }

    @Test
    public void getsBadRequestForNonSupportedMethod() {
        Endpoint endpoint = new EndpointStub();
        Request request = new Request("PATCH", "/false_endpoint", "HTTP/1.1");

        Response response = endpoint.getResponse(request);

        assertEquals(400, response.getStatusCode());
        assertEquals("BAD REQUEST", response.getReason());
        assertEquals("GET", response.getHeaders().get("Allow").getValue());
    }

    @Test
    public void getsNotAllowedForInexistentPostMethod() {
        assertMethodExistance("POST");
    }

    @Test
    public void getsNotAllowedForInexistentHeadMethod() {
        assertMethodExistance("HEAD");
    }

    @Test
    public void getsNotAllowedForInexistentOptionsMethod() {
        assertMethodExistance("OPTIONS");
    }

    @Test
    public void getsNotAllowedForInexistentPutMethod() {
        assertMethodExistance("PUT");
    }

    private void assertMethodExistance(String method) {
        Endpoint endpoint = new EndpointStub();
        Request req = new Request(method, "/false_endpoint", "HTTP/1.1");

        Response res = endpoint.getResponse(req);

        assertEquals(405, res.getStatusCode());
        assertEquals("NOT ALLOWED", res.getReason());
        assertEquals("GET", res.getHeaders().get("Allow").getValue());
    }
}
