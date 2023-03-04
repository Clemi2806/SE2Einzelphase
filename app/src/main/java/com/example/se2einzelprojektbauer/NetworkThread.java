package com.example.se2einzelprojektbauer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class NetworkThread extends Thread {

    NetworkListener listener;
    String message;

    NetworkThread(String message, NetworkListener listener){
        this.listener = listener;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket("se2-isys.aau.at", 53212);
            PrintStream writer = new PrintStream(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer.println(this.message);
            String response = reader.readLine();
            this.listener.onNetworkResponse(response);
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
