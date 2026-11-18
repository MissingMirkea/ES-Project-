package ro.foodwise.service;

import org.springframework.stereotype.Service;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;

@Service
public class ThreadAndSocketDemoService {
    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    public String threadedSocketRoundTrip(String message) throws IOException {
        try (ServerSocket server = new ServerSocket(0)) {
            Future<String> serverResponse = executor.submit(() -> {
                try (Socket socket = server.accept();
                     BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                     BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))) {
                    String received = input.readLine();
                    output.write("SERVER_RECEIVED: " + received);
                    output.newLine(); output.flush();
                    return received;
                }
            });
            try (Socket client = new Socket("127.0.0.1", server.getLocalPort());
                 BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
                 BufferedWriter output = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), StandardCharsets.UTF_8))) {
                output.write(message); output.newLine(); output.flush();
                String response = input.readLine();
                serverResponse.get(2, TimeUnit.SECONDS);
                return response;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IOException("Threadul socket a fost întrerupt.", e);
            } catch (ExecutionException | TimeoutException e) {
                throw new IOException("Serverul socket nu a răspuns.", e);
            }
        }
    }

    @jakarta.annotation.PreDestroy
    public void shutdown() { executor.shutdownNow(); }
}
