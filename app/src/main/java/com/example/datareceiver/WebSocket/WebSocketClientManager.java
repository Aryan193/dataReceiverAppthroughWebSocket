package com.example.datareceiver.WebSocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;

public class WebSocketClientManager extends WebSocketClient {

    private MessageListener messageListener;

    public WebSocketClientManager(URI serverURI, MessageListener listener) {
        super(serverURI);
        this.messageListener = listener;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connected to WebSocket Server");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Received message: " + message);
        if (messageListener != null) {
            messageListener.onMessageReceived(message);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Connection closed: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("WebSocket Error: " + ex.getMessage());
        ex.printStackTrace();
    }

    public interface MessageListener {
        void onMessageReceived(String message);
    }
}
