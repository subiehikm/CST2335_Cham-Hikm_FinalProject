package com.example.nasa_iotd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        Button searchButton = findViewById(R.id.homepage_search_button);
        // make visible the progress bar
        progressBar.setVisibility(View.VISIBLE);

        // TODO: Make an API Call to NASA and inform user when the JSON METADATA has arrived
        searchButton.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.INVISIBLE);

        GetDateQuery req = new GetDateQuery(startButton, AppDatabase.getDatabase(getApplicationContext()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String today = sdf.format(c.getTime());
        req.execute(today);
//        req.execute("2020-08-09");

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(
                        new Intent(MainActivity.this, com.example.nasa_iotd.ImageList.class)
                );
            }

        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // make visible the progress bar
                progressBar.setVisibility(View.GONE);

                // TODO: Maybe create a bundle with the UID of the current date's entry. But we do know it can be searched for (or the button wouldn't be visible)

                startActivity(
                        new Intent(MainActivity.this, com.example.nasa_iotd.DisplayImage.class)
                );
            }

        });
    }
}