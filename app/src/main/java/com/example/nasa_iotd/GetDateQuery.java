package com.example.nasa_iotd;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetDateQuery extends AsyncTask<String, Integer, String> {

    AppDatabase db;

    View toShow;
    public GetDateQuery(View show_when_finished, AppDatabase database){
        db = database;
        toShow = show_when_finished;
    }

    //Bitmap weatherImage = null;

    @Override
    protected String doInBackground(String... args) {
        String date = args[0];
        String base_url = "https://api.nasa.gov/planetary/apod?api_key=DgPLcIlnmN0Cwrzcg3e9NraFaYLIDI68Ysc6Zh3d&date=";

        URL url;
        HttpURLConnection urlConnection;
        InputStream response;
        try {
            //create a URL object of what server to contact:
            Log.d("URL_CALL", base_url.concat(date));
            url = new URL(base_url.concat(date));

            //open the connection
            urlConnection = (HttpURLConnection) url.openConnection();

            //wait for data:
            response = urlConnection.getInputStream();

            // Convert the response to a string
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new BufferedInputStream(response))
            );

            String json_string = "";
            String line = "";
            while ((line = br.readLine()) != null){
                json_string = json_string.concat(line);
            }

            // Convert the string from JSON to a DateEntry Object for storage in the database
            JSONObject json = new JSONObject(json_string);
            DateEntry entry = new DateEntry();
            entry.date = json.getString("date");
            entry.url = json.getString("url");
            entry.hdurl = json.getString("hdurl");
            entry.explanation = json.getString("explanation");
            entry.media_type = json.getString("media_type");
            entry.service_version = json.getString("service_version");
            entry.title = json.getString("title");
            db.dateentryDao().addDateEntry(entry);

            // Maybe trigger image download
            publishProgress(25);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // Really ugly hack; just try again - might be a transient failure.
            Log.w("RECURSION","Warning Infinite Recursion Possible.");
            return doInBackground(args);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // We have a Response; This is our json file - we need to parse it now.
//        DateEntry e = new DateEntry();

        /*
        String imagefile = icon + ".png";
        Log.i("Found", "File name is: " + imagefile);

        try {

            if (!fileExistence(imagefile)) {

                URL url = new URL("http://openweathermap.org/img/w/" + icon + ".png");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                int responseCode = connection.getResponseCode();

                if (responseCode == 200) {

                    weatherImage = BitmapFactory.decodeStream(connection.getInputStream());
                    FileOutputStream outputStream = openFileOutput( icon + ".png", Context.MODE_PRIVATE);
                    weatherImage.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                    outputStream.flush();
                    outputStream.close();
                    publishProgress(100);
                    Log.i("found", "Found Icon Name");

                }

            }

            else {

                FileInputStream fis = null;
                try {    fis = openFileInput(imagefile);   }
                catch (FileNotFoundException e) {    e.printStackTrace();  }
                weatherImage = BitmapFactory.decodeStream(fis);
                Log.i("found", "Found Icon locally");


            }

        }

        catch (Exception e) {

            Log.e("Error", e.getMessage());
        }

        try {

            URL url = new URL("http://api.openweathermap.org/data/2.5/uvi?appid=7e943c97096a9784391a981c4d878b22&lat=45.348945&lon=-75.759389");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {

                JSONObject jObject = new JSONObject(String.valueOf(connection.getInputStream()));

                uv = jObject.getDouble("value");

            }

        }

        catch (Exception e) {

            Log.e("Error", e.getMessage());
        }
         */

        return "Done";

    }

//    public boolean fileExistence(String fname) {
////        File file = getBaseContext().getFileStreamPath(fname);
////        return file.exists();
//    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        if (values[0] >= 25) {
            toShow.setVisibility(View.VISIBLE);
        }
//        pb.setVisibility(View.VISIBLE);
//        pb.setProgress(values[0]);

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
//        txt_min.setText(min);
//        txt_max.setText(max);
//        txt_current.setText(value);
//        txt_UV.setText(String.valueOf(uv));
//        icon_holder.setImageBitmap(weatherImage);
//        pb.setVisibility(View.INVISIBLE);

    }
}
