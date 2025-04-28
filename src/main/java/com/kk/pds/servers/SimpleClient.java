package com.kk.pds.servers;
import java.io.*;
import java.net.Socket;

public class SimpleClient {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // or server IP
        int port = 12345;

        try (
                Socket socket = new Socket(serverAddress, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            out.println("Hello From Simple Client!"); // Sending payload
            String response = in.readLine(); // Reading response
            System.out.println("Server responded: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
