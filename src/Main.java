import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

import javax.imageio.ImageIO;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Servidor REST iniciado en http://localhost:8080");

        // 1. Crear servidor REST
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // 2. Ruta /qr
        server.createContext("/qr", (HttpExchange exchange) -> {
            try {
                // Generar el QR
                QRGenerator.QRResult qr = QRGenerator.generate();

                System.out.println("Petición recibida → texto generado: " + qr.text);

                // Cabeceras HTTP
                exchange.getResponseHeaders().set("Content-Type", "image/png");
                exchange.sendResponseHeaders(200, 0);

                // Enviar imagen al cliente
                OutputStream os = exchange.getResponseBody();
                ImageIO.write(qr.image, "PNG", os);
                os.close();

            } catch (Exception e) {
                e.printStackTrace();
                exchange.sendResponseHeaders(500, 0);
                exchange.getResponseBody().close();
            }
        });

        // 3. Iniciar
        server.start();
    }
}
