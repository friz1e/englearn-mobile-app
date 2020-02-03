package com.example.englearn;

import android.os.AsyncTask;

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

    private StringBuilder contentBuilder = null;
    private String receivedString = null;

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL("https://translate.googleapis.com/translate_a/t?client=p&sl=en&tl=pl&dt=t&q=" + wordInserted);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.addRequestProperty("User-Agent", "REST-API");

            InputStream inStream = urlConnection.getInputStream();

            contentBuilder = new StringBuilder();
            try {
                BufferedReader receivedJSON = new BufferedReader(new InputStreamReader(inStream));
                String str;
                while((str = receivedJSON.readLine()) != null) {
                    contentBuilder.append(str);
                }
                inStream.close();
            }

            catch(IOException e) {
                e.getMessage();
            }

            receivedString = contentBuilder.toString();
            receivedString = receivedString.replaceAll("\"\"", "");


            return receivedString;

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return receivedString;
    }

}
