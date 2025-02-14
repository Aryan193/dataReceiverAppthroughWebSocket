package com.example.datareceiver;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.datareceiver.WebSocket.WebSocketClientManager;
import com.example.datareceiver.databinding.ActivityMainBinding;

import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private WebSocketClientManager client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
            client = new WebSocketClientManager(new URI("ws://192.168.1.3:8080"), message ->
                    runOnUiThread(() -> binding.tvReceived.setText(message))
            );
            client.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (client != null) {
            client.close();
        }
    }
}