package com.example.nasa_iotd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ImageList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        // TODO: Populate the list using the entries from the database
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        List<DateEntry> entries = db.dateentryDao().getAllDateEntries();

        // TODO: Create a list adaptor and make the entries list available in the view
        ListView lv = findViewById(R.id.theListView);
        ArrayAdapter<DateEntry> adapter = new ArrayAdapter<DateEntry>(this, android.R.layout.simple_list_item_1, entries.toArray(new DateEntry[0]));
        lv.setAdapter(adapter);

        // Click Adaptor for the list so you can go to DisplayImage with the UID stored in a bundle
            // In order to display that image.
    }

}

    //Button button;

//Toast.makeText(Context, “Message String”, int duration).show();

    //@Override
    //public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {

        //Snackbar snackbar;
        //snackbar = Snackbar.make(parentLayout , getResources().getString(R.string.checkbox_message) + (b ? getResources().getString(R.string.on) : getResources().getString(R.string.off)), Snackbar.LENGTH_SHORT);
        //snackbar.setAction("UNDO", new View.OnClickListener() {
           // @Override
            //public void onClick(View view) {
               // compoundButton.setChecked(!b);
          //  }
        //});
        //snackbar.show();
    //@Override
    //protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);

        //SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);

        //}

        //Button btnClickHere = findViewById(R.id.button);
        //btnClickHere.setOnClickListener(new View.OnClickListener() {

            //@Override
            //public void onClick(View view) {

                //Toast.makeText(ImageList.this, getResources().getString(R.string.toast_message) , Toast.LENGTH_SHORT).show();
           // }
        //});

