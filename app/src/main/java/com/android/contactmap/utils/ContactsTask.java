package com.android.contactmap.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.android.contactmap.data.ContactData;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * @author DEEPANKAR
 * @since 17-04-2016.
 */
public class ContactsTask extends AsyncTask<Void, Void, Void> {

    public static final String TAG = "ContactsTask";

    public OnContactLoadListener mListener;

    public ContactsTask(OnContactLoadListener loadListener){
        this.mListener = loadListener;
    }

    @Override
    protected Void doInBackground(Void... params) {

        HttpURLConnection urlConnection;
        InputStream inputStream;
        try {
            URL url = new URL("http://private-b08d8d-nikitest.apiary-mock.com/contacts");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");

            urlConnection.setRequestMethod("GET");
            int statusCode = urlConnection.getResponseCode();

            if (statusCode ==  200) {
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
                parseResult(inputStream);
            }

        } catch (Exception e) {
            mListener.onFailure(e);
            Log.e(TAG, e.getMessage());
        }

        return null;
    }

    private void parseResult(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line;
        String result = "";
        while((line = bufferedReader.readLine()) != null){
            result += line;
        }
        inputStream.close();

        try {
            JSONArray jsonArray = new JSONArray(result);
            jsonArray = jsonArray.getJSONObject(0).getJSONArray("contacts");
            Gson gson = new Gson();
            List<ContactData> list = Arrays.asList(gson.fromJson(jsonArray.toString(), ContactData[].class));
            mListener.onSuccess(list);

        } catch (JSONException e) {
            mListener.onFailure(e);
        }

    }


}
