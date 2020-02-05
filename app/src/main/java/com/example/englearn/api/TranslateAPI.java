package com.example.englearn.api;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TranslateAPI extends AsyncTask<String, String, String>{

    public String wordInserted = null;

    public TranslateAPI(String word) {
        this.wordInserted = word;
    }

    private String receivedString = null;
    private String receivedJSON = null;

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL("https://api.mymemory.translated.net/get?q=" + wordInserted + "!&langpair=en|pl");
            URLConnection urlConnection = url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.addRequestProperty("User-Agent", "REST");

            InputStream inStream = urlConnection.getInputStream();

            receivedJSON = new BufferedReader(new InputStreamReader(inStream)).readLine();
            inStream.close();

            try {
                JSONObject response = new JSONObject(receivedJSON);
                JSONObject objectWithTranslatedWord = response.getJSONObject("responseData");
                receivedString = objectWithTranslatedWord.getString("translatedText");
                receivedString = receivedString.replaceAll("[\"\",:,?,!,';,-,/]", "");
            } catch(JSONException ex) {
                ex.printStackTrace();
            }

            System.out.println(inStream);

            receivedString = receivedString.toLowerCase();

            return receivedString;

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return receivedString;
    }

}
