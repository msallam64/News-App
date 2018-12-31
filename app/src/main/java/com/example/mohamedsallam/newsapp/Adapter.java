package com.example.mohamedsallam.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mohamed Sallam on 27-Feb-18.
 */

public class Adapter extends ArrayAdapter<News> {
    public Adapter(Context context) {
        super(context, -1, new ArrayList<News>());
    }

    @Override
    public View getView(int position, View root, ViewGroup parent) {
        if (root == null) {
            root = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_item, parent, false);
        }
        TextView date = (TextView) root.findViewById(R.id.tv_date);
        TextView section = (TextView) root.findViewById(R.id.tv_section);
        TextView title = (TextView) root.findViewById(R.id.tv_title);
        TextView author = (TextView) root.findViewById(R.id.tv_author);
        News news = getItem(position);
        title.setText(news.getTitle());
        author.setText(news.getAuthor());
        date.setText(news.getDate());
        section.setText(news.getSection());
        return root;
    }
}
