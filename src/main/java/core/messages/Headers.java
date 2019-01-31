package core.messages;

import java.util.*;

public class Headers {
    private Map<String, String> headers;

    public Headers() {
        this.headers = new LinkedHashMap<>();
    }

    public void addAll(Headers headers) {
        this.headers.putAll(headers.getHeadersMap());
    }

    public void add(String name, String value) {
        headers.put(name, value);
    }

    public String get(String name) {
        return this.headers.get(name);
    }

    @Override
    public String toString() {
        StringBuilder formattedHeaders = new StringBuilder();
        for (String headerName : headers.keySet()) {
            formattedHeaders.append(String.format("%s: %s%n", headerName, headers.get(headerName)));
        }

        return formattedHeaders.toString();
    }

    private Map<String, String> getHeadersMap() {
        return headers;
    }
}

