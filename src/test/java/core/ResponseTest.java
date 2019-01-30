package core;

import core.models.Response;
import core.models.ResponseHeader;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class ResponseTest {
    @Test
    public void hasValidStatusLineInformation() {
        Response res = Response.ok();

        assertEquals("HTTP/1.1", res.getHttpVersion());
        assertEquals(200, res.getStatusCode());
        assertEquals("OK", res.getReason());
    }

    @Test
    public void buildsResponseBasicResponse() {
        Response res = Response.ok();

        assertEquals("HTTP/1.1 200 OK\nServer: Javier's awesome server\n", res.toString());
    }

    @Test
    public void buildsResponseWithBody() {
        Response res = Response.redirect()
                .withBody("some body");

        assertEquals("HTTP/1.1 301 REDIRECT\nServer: Javier's awesome server\n\nsome body", res.toString());
    }

    @Test
    public void buildsResponseWithHeaders() {
        Map<ResponseHeader, String> headers = new LinkedHashMap<>();
        headers.put(ResponseHeader.LOCATION, "value1");
        headers.put(ResponseHeader.ALLOW, "value2");

        Response res = Response.ok()
                .withHeaders(headers)
                .withBody("some body");

        assertEquals("HTTP/1.1 200 OK\nServer: Javier's awesome server\nLocation: value1\nAllow: value2\n\nsome body", res.toString());
    }

    @Test
    public void buildsResponseWithSingleHeaders() {
        Response res = Response.ok()
                .withHeader(ResponseHeader.LOCATION, "value1")
                .withHeader(ResponseHeader.ALLOW, "value2");

        assertEquals("HTTP/1.1 200 OK\nServer: Javier's awesome server\nLocation: value1\nAllow: value2\n", res.toString());
    }

    @Test
    public void createsInternalErrorResponse() {
        Response res = Response.internalError();

        assertEquals("INTERNAL SERVER ERROR", res.getReason());
        assertEquals(500, res.getStatusCode());
    }
}