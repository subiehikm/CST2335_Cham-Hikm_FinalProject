package com.example.nasa_iotd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    public static final String ACTIVITY_NAME = "MAIN_ACTIVITY";
    ProgressBar progressBar;
    Button entryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.MainLoading);
        Button startButton = findViewById(R.id.startBtn);

        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // make visible the progress bar
                progressBar.setVisibility(View.VISIBLE);
                startActivity(
                        new Intent(MainActivity.this, com.example.nasa_iotd.DisplayImage.class)
                );
            }

        });

        entryButton = (Button) findViewById(R.id.startBtn);
        //View chat room Button;
        entryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(ACTIVITY_NAME, "launching chat room intent");
                startActivity(new Intent(MainActivity.this, DisplayImage.class));
            }
        });

}
}