package core.messages;

import java.util.*;

public class Headers {
    private Map<String, Header> headers;

    public Headers() {
        this.headers = new LinkedHashMap<>();
    }

    public void addAll(Headers headers) {
        for(Header header : headers.asCollection()) {
            add(header);
        }
    }

    public void add(Header header) {
        this.headers.put(header.getName(), header);
    }

    public void add(String name, String value) {
        Header header = new Header(name, value);
        add(header);
    }

    public Header get(String name) {
    return this.headers.get(name);
    }

    @Override
    public String toString() {
        StringBuilder formattedHeaders = new StringBuilder();
        for (Header header : headers.values()) {
            formattedHeaders.append(String.format("%s: %s%n", header.getName(), header.getValue()));
        }

        return formattedHeaders.toString();
    }

    private Collection<Header> asCollection() {
        return this.headers.values();
    }
}

