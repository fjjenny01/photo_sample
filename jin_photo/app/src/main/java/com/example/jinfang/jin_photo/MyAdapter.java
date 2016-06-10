package com.example.jinfang.jin_photo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jinfang on 6/9/16.
 */
public class MyAdapter extends ArrayAdapter<String> {
    private  String[] books;
    private  Activity context;
    private  Integer[] imageId;

    public MyAdapter(Activity context, String[] books, Integer[] imageId){
        super(context, R.layout.list_single, books);
        this.context = context;
        this.books = books;
        this.imageId = imageId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        return super.getView(position, convertView, parent);

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(books[position]);

        imageView.setImageResource(imageId[position]);
        return rowView;

    }
}
