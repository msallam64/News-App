package com.example.mohamedsallam.newsapp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by Mohamed Sallam on 27-Feb-18.
 */

public class NewsLoader extends AsyncTaskLoader<ArrayList<News>> {

    public NewsLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<News> loadInBackground() {
        ArrayList<News> listOfNews = null;
        try {
            URL url = Networking.createUrl();
            String responeJson = Networking.makeHttpRequest(url);
            listOfNews = Networking.extractfromjson(responeJson);
        } catch (IOException e) {
        }
        return listOfNews;
    }
}
