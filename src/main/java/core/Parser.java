package core;

import core.exceptions.HttpParseException;
import core.models.Headers;
import core.models.Request;

public class Parser {
    public static Request parse(String requestString) throws HttpParseException {
        try {
            Request request = new Request();

            String[] requestLine = parseRequestLine(requestString);
            request.withMethod(requestLine[0])
                    .withUri(requestLine[1])
                    .withHttpVersion(requestLine[2]);

            Headers headers = parseHeaders(requestString);
            request.withHeaders(headers);

            String body = parseBody(requestString);
            request.withBody(body);

            return request;
        } catch (Exception ex) {
            throw new HttpParseException(ex);
        }
    }

    private static String[] parseRequestLine(String requestString) {
        String[] lines = requestString.split("\n");
        return lines[0].split(" ");
    }

    private static Headers parseHeaders(String requestString) {
        String[] parts = requestString.split("\r\n\r\n");
        String[] headersArray = parts[0].split("\n");

        Headers headers = new Headers();
        for (int i = 1; i < headersArray.length; i++) { // Skip first element -> HTTP requestLine.
            String[] header = headersArray[i].split(":");
            headers.add(header[0].trim(), header[1].trim());
        }

        return headers;
    }

    private static String parseBody(String requestString) {
        String[] parts = requestString.split("\r\n\r\n");
        return parts.length == 2 ? parts[1] : null; // No body is not the same as empty body.
    }
}
