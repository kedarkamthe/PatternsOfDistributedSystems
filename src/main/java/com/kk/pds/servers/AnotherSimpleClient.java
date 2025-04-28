package com.kk.pds.servers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AnotherSimpleClient {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // or server IP
        int port = 12345;

        try (
                Socket socket = new Socket(serverAddress, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            out.println("Hello From Another Simple Client!"); // Sending payload
            String response = in.readLine(); // Reading response
            System.out.println("Server responded: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
