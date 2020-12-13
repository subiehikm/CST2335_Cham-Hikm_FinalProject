package com.example.nasa_iotd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ImageList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        // TODO: Populate the list using the entries from the database
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        List<DateEntry> entries = db.dateEntryDao().getAllDateEntries();

        // TODO: Create a list adaptor and make the entries list available in the view
        ListView lv = findViewById(R.id.theListView);
        final ArrayAdapter<DateEntry> adapter = new ArrayAdapter<DateEntry>(this, android.R.layout.simple_list_item_1, entries.toArray(new DateEntry[0]));
        lv.setAdapter(adapter);

        // Click Adaptor for the list so you can go to DisplayImage with the UID stored in a bundle
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle args = new Bundle();
                args.putLong("dateId", l);

                Intent nextActivity = new Intent(ImageList.this, com.example.nasa_iotd.DisplayImage.class);
                nextActivity.putExtras(args); //send data to next activity
                startActivity(nextActivity); //make the transition
            }
        });

         EditText editText = findViewById(R.id.search_bar);

         editText.setText(this.getSharedPreferences("ImageSearch", MODE_PRIVATE).getString("lastSearchDate", ""));

         Button searchBtn = findViewById(R.id.searchButton);
         searchBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                GetDateQuery q = new GetDateQuery(searchBtn, db, ImageList.this);
                searchBtn.setVisibility(View.GONE);
                ImageList.this.getSharedPreferences("ImageSearch", MODE_PRIVATE).edit().putString("lastSearchDate", (editText.getEditableText().toString())).apply();
                q.execute(editText.getEditableText().toString());
                 Toast.makeText(ImageList.this, "Searching for Image of the day for "+ editText.getEditableText().toString(), Toast.LENGTH_LONG).show();

             }
         });

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

