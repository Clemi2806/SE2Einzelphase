package com.example.se2einzelprojektbauer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity implements NetworkListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonEvent(View view){
        TextView textViewMatNr = (TextView) findViewById(R.id.textFieldMatNr);
        String matNr = textViewMatNr.getText().toString();
        NetworkThread nt = new NetworkThread(matNr, this);
        nt.start();
    }

    @Override
    public void onNetworkResponse(String response) {
        runOnUiThread(() -> {
            TextView responseView = (TextView) findViewById(R.id.textServerResponse);
            responseView.setText(response);
        });
    }
}