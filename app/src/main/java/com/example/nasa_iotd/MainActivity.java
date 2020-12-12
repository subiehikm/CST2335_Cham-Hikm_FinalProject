package com.example.nasa_iotd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String ACTIVITY_NAME = "MAIN_ACTIVITY";
    ProgressBar progressBar;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.MainLoading);
        Button startButton = findViewById(R.id.startBtn);
        // make visible the progress bar
        progressBar.setVisibility(View.VISIBLE);

        // TODO: Make an API Call to NASA and inform user when the JSON METADATA has arrived
        startButton.setVisibility(View.GONE);

        GetDateQuery req = new GetDateQuery(startButton, AppDatabase.getDatabase(getApplicationContext()));
        req.execute("2020-12-11");

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // make visible the progress bar
                progressBar.setVisibility(View.GONE);

                startActivity(
                        new Intent(MainActivity.this, com.example.nasa_iotd.DisplayImage.class)
                );
            }

        });
    }
}