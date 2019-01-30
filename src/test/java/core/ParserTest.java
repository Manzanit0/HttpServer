package core;

import core.exceptions.HttpParseException;
import core.models.Headers;
import core.models.Request;
import core.models.RequestMethod;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ParserTest {

    @Test
    public void parseRequestWithoutHeaders() throws HttpParseException {
        String requestString = "GET /helloworld HTTP/1.1\n";

        Request request = new Request(RequestMethod.GET, "/helloworld", "HTTP/1.1");
        Request parsedRequest = Parser.parse(requestString);

        assertEquals(request.getMethod(), parsedRequest.getMethod());
        assertEquals(request.getHttpVersion(), parsedRequest.getHttpVersion());
        assertEquals(request.getUri(), parsedRequest.getUri());
    }

    @Test
    public void parseRequestWithHeaders() throws HttpParseException {
        String requestString =
                "GET /helloworld HTTP/1.1\n" +
                        "User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\n" +
                        "Host: localhost\n" +
                        "Connection: Keep-Alive";

        Headers headers = new Headers();
        headers.add("User-Agent", "Mozilla/4.0 (compatible; MSIE5.01; Windows NT)");
        headers.add("Host", "localhost");
        headers.add("Connection", "Keep-Alive");
        Request request = new Request(RequestMethod.GET, "/helloworld", "HTTP/1.1")
                .withHeaders(headers);

        Request parsedRequest = Parser.parse(requestString);

        assertEquals(request.getMethod(), parsedRequest.getMethod());
        assertEquals(request.getHttpVersion(), parsedRequest.getHttpVersion());
        assertEquals(request.getUri(), parsedRequest.getUri());
        assertEquals(request.getHeaders().get("Host").getValue(), parsedRequest.getHeaders().get("Host").getValue());
        assertEquals(request.getHeaders().get("Connection").getValue(), parsedRequest.getHeaders().get("Connection").getValue());
        assertEquals(request.getHeaders().get("User-Agent").getValue(), parsedRequest.getHeaders().get("User-Agent").getValue());
    }

    @Test
    public void parseRequestWithHeadersAndBody() throws HttpParseException {
        String requestString =
                "POST localhost HTTP/1.1\n" +
                        "Content-Type: application/text\n" +
                        "Content-Length: 456\n" +
                        "\r\n\r\n" +
                        "Some body";

        Headers headers = new Headers();
        headers.add("Content-Type", "application/text");
        headers.add("Content-Length", "456");
        Request request = new Request(RequestMethod.POST, "localhost", "HTTP/1.1")
                .withHeaders(headers)
                .withBody("Some body");

        Request parsedRequest = Parser.parse(requestString);

        assertEquals(request.getMethod(), parsedRequest.getMethod());
        assertEquals(request.getHttpVersion(), parsedRequest.getHttpVersion());
        assertEquals(request.getUri(), parsedRequest.getUri());
        assertEquals(request.getHeaders().get("Content-Type").getValue(), parsedRequest.getHeaders().get("Content-Type").getValue());
        assertEquals(request.getHeaders().get("Content-Length").getValue(), parsedRequest.getHeaders().get("Content-Length").getValue());
        assertEquals(request.getBody(), parsedRequest.getBody());
    }

    @Test
    public void throwsExceptionUponUnparseableRequest() {
        try {
            Parser.parse("Some-random-code.");
            assertTrue(false);
        } catch (HttpParseException ex) {
            assertTrue(true);
        }
    }
}