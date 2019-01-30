package core;

import core.messages.RequestMethod;
import core.messages.Response;
import org.junit.Test;
import stubs.ConnectionStub;
import stubs.RouterStub;
import stubs.ServerStub;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;

public class ServerTest {
    @Test
    public void serverIsAlwaysRunning() {
        Server server = Server.defaultServer(5200);
        assertTrue(server.isRunning());
    }

    @Test
    public void createsDefaultInstanceWithoutExceptions() {
        Server server = Server.defaultServer(5201);
        assertNotEquals(null, server);
        assertNotEquals(null, server.getRouter());
    }

    @Test
    public void throwsExceptionIfPortIsUsed() {
        Server server = Server.defaultServer(5202);

        try {
            Server.defaultServer(5202);
            assertTrue(false);
        }catch (RuntimeException ex) {
            assertTrue(true);
        }
    }

    @Test
    public void pipesConnectionParserAndRouter() {
        String[] mockMessages = new String[]{"GET /hello-world HTTP/1.1\n"};
        ConnectionStub connectionStub = ConnectionStub.createWithMessages(mockMessages);

        Response[] mockResponses = new Response[]{Response.ok()};
        RouterStub routerStub = RouterStub.createWithResponses(mockResponses);

        Server server = new Server(connectionStub, routerStub);

        server.handleRequest();

        assertEquals(RequestMethod.GET, routerStub.getLastRequest().getMethod());
        assertEquals("/hello-world", routerStub.getLastRequest().getUri());
        assertTrue(connectionStub.getLastResponse().contains("OK"));
    }

    @Test
    public void uponHandlingNonHttpRequestReturnsBadRequest() {
        String[] mockMessages = new String[]{"some-non-http-message"};
        ConnectionStub connectionStub = ConnectionStub.createWithMessages(mockMessages);

        RouterStub routerStub = new RouterStub();
        Server server = new Server(connectionStub, routerStub);

        server.handleRequest();

        assertTrue(connectionStub.getLastResponse().contains("BAD REQUEST"));
    }

    @Test
    public void processesMultipleRequests() {
        String[] mockMessages = new String[]{"GET /hello-world HTTP/1.1\n", "GET /bye-world HTTP/1.1\n"};
        ConnectionStub connectionStub = ConnectionStub.createWithMessages(mockMessages);

        Response[] mockResponses = new Response[]{Response.ok(), Response.redirect()};
        RouterStub routerStub = RouterStub.createWithResponses(mockResponses);

        ServerStub server = new ServerStub(connectionStub, routerStub);
        server.setRequestsUntilShutdown(2);

        server.start();

        assertEquals(RequestMethod.GET, routerStub.getLastRequest().getMethod());
        assertEquals("/bye-world", routerStub.getLastRequest().getUri());
        assertTrue(connectionStub.getLastResponse().contains("REDIRECT"));
    }
}