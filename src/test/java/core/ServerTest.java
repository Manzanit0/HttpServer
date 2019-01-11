package core;

import core.models.Response;
import org.junit.Test;
import stubs.ConnectionStub;
import stubs.RouterStub;
import stubs.ServerStub;

import java.util.LinkedList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;

public class ServerTest {
    @Test
    public void createsDefaultInstanceWithoutExceptions() {
        Server server = Server.defaultServer(5202);
        assertNotEquals(null, server);
        assertNotEquals(null, server.getRouter());
    }

    @Test
    public void pipesConnectionParserAndRouter() {
        LinkedList<String> messages = new LinkedList<>();
        messages.add("GET /hello-world HTTP/1.1\n");
        ConnectionStub connectionStub = new ConnectionStub(null);
        connectionStub.setIncomingRequests(messages);

        LinkedList<Response> responses = new LinkedList<>();
        responses.add(Response.ok());
        RouterStub routerStub = new RouterStub();
        routerStub.setResponses(responses);

        Server server = new Server(connectionStub, routerStub);

        server.handleRequest();

        assertEquals("GET", routerStub.getLastRequest().getMethod());
        assertEquals("/hello-world", routerStub.getLastRequest().getUri());
        assertTrue(connectionStub.getLastResponse().contains("OK"));
    }

    @Test
    public void uponHandlingNonHttpRequestReturnsBadRequest() {
        LinkedList<String> messages = new LinkedList<>();
        messages.add("some-non-http-message");
        ConnectionStub connectionStub = new ConnectionStub(null);
        connectionStub.setIncomingRequests(messages);

        RouterStub routerStub = new RouterStub();
        Server server = new Server(connectionStub, routerStub);

        server.handleRequest();

        assertTrue(connectionStub.getLastResponse().contains("BAD REQUEST"));
    }

    @Test
    public void processesMultipleRequests() {
        LinkedList<String> messages = new LinkedList<>();
        messages.add("GET /hello-world HTTP/1.1\n");
        messages.add("GET /bye-world HTTP/1.1\n");
        ConnectionStub connectionStub = new ConnectionStub(null);
        connectionStub.setIncomingRequests(messages);

        LinkedList<Response> responses = new LinkedList<>();
        responses.add(Response.ok());
        responses.add(Response.redirect());
        RouterStub routerStub = new RouterStub();
        routerStub.setResponses(responses);

        ServerStub server = new ServerStub(connectionStub, routerStub);
        server.setRequestsUntilShutdown(2);

        server.start();

        assertEquals("GET", routerStub.getLastRequest().getMethod());
        assertEquals("/bye-world", routerStub.getLastRequest().getUri());
        assertTrue(connectionStub.getLastResponse().contains("REDIRECT"));
    }
}