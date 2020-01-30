package com.example.englishteacher;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TranslateAPI extends AsyncTask<String, String, String>{

    AddActivity addActivity;
    public String wordInserted = null;

    public TranslateAPI(String word) {
        this.wordInserted = word;
    }

    public static final String API_KEY = "";
    private String receivedJSON = null;
    private String receivedString = null;

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL("https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + API_KEY + "&text=" + wordInserted +"&lang=en-pl");
            URLConnection urlConnection = url.openConnection();
            urlConnection.addRequestProperty("User-Agent", "REST-API");

            InputStream inStream = urlConnection.getInputStream();

            receivedJSON = new BufferedReader(new InputStreamReader(inStream)).readLine();
            inStream.close();

            try {
                JSONObject response = new JSONObject(receivedJSON);
                receivedString = response.getString("text");
                receivedString = receivedString.replaceAll("[\\[\\]\"]", "");
                System.out.println(receivedString);
            } catch(JSONException ex) {
                ex.printStackTrace();
            }

            return receivedString;


        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return receivedString;
    }

}
