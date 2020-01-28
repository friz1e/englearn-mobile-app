package com.example.englishteacher;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TranslateAPI extends AsyncTask<String, String, String> {

    public static final String API_KEY = "";
    private String receivedJSON = null;
    public String wordInserted = null;

    @Override
    protected String doInBackground(String... strings) {
        try {
            System.out.println(wordInserted+"3");
            URL url = new URL("https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + API_KEY + "&text=" + wordInserted +"&lang=en-pl");
            URLConnection urlConnection = url.openConnection();
            urlConnection.addRequestProperty("User-Agent", "REST-API");

            InputStream inStream = urlConnection.getInputStream();

            receivedJSON = new BufferedReader(new InputStreamReader(inStream)).readLine();
            inStream.close();

            return receivedJSON;

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return receivedJSON;
    }

    @Override
    protected void onPostExecute(String s) {
        System.out.println(receivedJSON);
    }
}
