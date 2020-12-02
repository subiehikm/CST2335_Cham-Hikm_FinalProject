package com.example.nasa_iotd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressBar progressBar = findViewById(R.id.MainLoading);
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

}
}