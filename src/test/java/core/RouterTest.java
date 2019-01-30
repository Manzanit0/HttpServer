package core;

import core.models.Request;
import core.models.RequestMethod;
import core.models.Response;
import org.junit.Before;
import org.junit.Test;
import stubs.EndpointStub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RouterTest {
    private Router router;

    @Before
    public void setUp() {
        router = new Router();
        Endpoint endpoint = new EndpointStub();
        router.add(endpoint);
    }

    @Test
    public void addsEndpoint() {
        // The setup method already adds a stub endpoint.
        assertEquals(1, router.getEndpoints().size());
        assertTrue(router.getEndpoints().containsKey("/false_endpoint"));
    }

    @Test
    public void getsCorrectResponseForExistingEndpoint() {
        Request request = new Request(RequestMethod.GET, "/false_endpoint", "HTTP/1.1");

        Response response = router.getResponse(request);
        assertEquals(200, response.getStatusCode());
        assertEquals("OK", response.getReason());
    }

    @Test
    public void getsNotFoundResponseForNonExistingEndpoint() {
        Request request = new Request(RequestMethod.POST, "/this_does_not_exist", "HTTP/1.1");

        Response response = router.getResponse(request);
        assertEquals(404, response.getStatusCode());
        assertEquals("NOT FOUND", response.getReason());
    }
}
