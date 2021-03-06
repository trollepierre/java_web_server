import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class InnWeb {

    public static void main(String[] args) throws Exception {

        int port = Integer.parseInt(System.getProperty("app.port", "8080"));
        HttpServer server = HttpServer.create(new InetSocketAddress(port),port);
        server.createContext("/", (HttpExchange exchange) -> {
            String uri = exchange.getRequestURI().toString();

            switch (uri) {
                case "/": {
                    byte[] body = "Hello World".getBytes();
                    exchange.getResponseHeaders().add("Content-Type", "text/html");
                    exchange.sendResponseHeaders(200, body.length);
                    exchange.getResponseBody().write(body);
                    break;
                }
                default: {
                    exchange.sendResponseHeaders(404, 0);
                }
            }
            exchange.close();
        });
        server.start();
    }
}