package com.example.englishteacher;

import android.os.AsyncTask;
import android.renderscript.Allocation;

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

    private StringBuilder contentBuilder = null;
    private String receivedString = null;

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL("https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=pl&dt=t&q=" + receivedString);
            URLConnection urlConnection = url.openConnection();
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

            try {
                JSONArray jsonArray = new JSONArray(receivedString);
                receivedString = jsonArray.getString(0);
                receivedString = receivedString.substring(receivedString.indexOf("[[\"")+3, receivedString.indexOf("]]")-20);
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
