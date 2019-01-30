package stubs;

import core.Router;
import core.messages.Request;
import core.messages.Response;

import java.util.Arrays;
import java.util.LinkedList;

public class RouterStub extends Router {
    private LinkedList<Response> responses;
    private Request lastRequest;

    public RouterStub() {
        responses = new LinkedList<>();
    }

    public static RouterStub createWithResponses(Response[] messages) {
        RouterStub router = new RouterStub();

        LinkedList<Response> messagesList = new LinkedList<>();
        messagesList.addAll(Arrays.asList(messages));
        router.setResponses(messagesList);

        return router;
    }

    @Override
    public Response getResponse(Request request) {
        lastRequest = request;

        Response response = responses.getFirst();
        responses.removeFirst();
        return response;
    }

    public void setResponses(LinkedList<Response> responses) {
        this.responses = responses;
    }

    public Request getLastRequest() {
        return lastRequest;
    }
}
