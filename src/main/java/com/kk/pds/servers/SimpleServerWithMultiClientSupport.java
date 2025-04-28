package com.kk.pds.servers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleServerWithMultiClientSupport {
    public static void main(String[] args) {
        int port = 12345; // Server will listen on this port
        int numberOfThreadsToRun = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreadsToRun);

        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("Server started. Listening on port " + port);

                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from " + clientSocket.getInetAddress());
                // Handle client connection in a separate thread
                executorService.submit(() -> handleClient(clientSocket));
                //new Thread(() -> handleClient(clientSocket)).start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received payload: " + inputLine);

                // --- Parsing payload ---
                String response = parsePayload(inputLine);

                // Send back a response
                out.println(response);

                // Optional: Close after one request-response, or continue for multiple messages
                // Uncomment to handle one message and close:
                // break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                System.out.println("Connection closed.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String parsePayload(String payload) {
        // Example parser: Just echoing back with some prefix
        // You can modify this function to parse JSON, XML, custom formats, etc.
        return "Server received: " + payload;
    }
}
