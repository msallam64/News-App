package com.example.mohamedsallam.newsapp;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Networking {

    public static URL createUrl() {
        String Url = buildUrl();
        try {
            return new URL(Url);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    public static String buildUrl() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .encodedAuthority("content.guardianapis.com")
                .appendPath("search")
                .appendQueryParameter("api-key", "e8f1ee02-bc63-4468-b742-48f3c1cae013")
                .appendQueryParameter("order-by", "newest")
                .appendQueryParameter("show-references", "author")
                .appendQueryParameter("show-tags", "contributor")
                .appendQueryParameter("q", "Android");
        String url = builder.build().toString();
        return url;
    }

    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e("Networking", "Error in response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static ArrayList<News> extractfromjson(String response) {
        ArrayList<News> news = new ArrayList<>();
        try {
            JSONObject mydata = new JSONObject(response);
            JSONObject info = mydata.getJSONObject("response");
            JSONArray results = info.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                JSONObject myinfo = results.getJSONObject(i);
                String title = myinfo.getString("webTitle");
                String newsUrl = myinfo.getString("webUrl");
                String date = myinfo.getString("webPublicationDate");
                String section = myinfo.getString("sectionName");
                JSONArray tagsArr = myinfo.getJSONArray("tags");
                String authorName = "";
                if (tagsArr.length() == 0) {
                    authorName = null;
                } else {
                    for (int j = 0; j < tagsArr.length(); j++) {
                        JSONObject object = tagsArr.getJSONObject(j);
                        authorName += object.getString("webTitle") + ".";
                    }
                }
                news.add(new News(title, section, authorName, date, newsUrl));
            }
        } catch (JSONException e) {
        }
        return news;
    }


}
