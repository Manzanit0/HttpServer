package core;

import core.exceptions.HttpParseException;
import core.messages.Request;
import core.messages.Response;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private Connection connection;
    private Router router;

    public Server(Connection connection, Router router) {
        this.connection = connection;
        this.router = router;
    }

    public static Server defaultServer(int port) {
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Connection conn = new Connection(serverSocket);
        Router router = new Router();
        return new Server(conn, router);
    }

    public Router getRouter() {
        return router;
    }

    public void start() {
        while (isRunning()) {
            connection.accept();
            handleRequest();
            connection.close();
        }
    }

    public void handleRequest() {
        Response responseModel;
        try {
            String request = connection.receive();
            Request requestModel = Parser.parse(request);
            responseModel = router.getResponse(requestModel);
        } catch (HttpParseException ex) {
            responseModel = Response.badRequest();
        }

        connection.send(responseModel.toString());
    }

    protected boolean isRunning() {
        return true;
    }
}