package com.example.jinfang.jin_photo;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jinfang on 6/9/16.
 */
public class MyAdapter extends ArrayAdapter<Book> {
    private List<Book> books;
//    private  String[] books;
    private  Activity context;
    private  Integer[] imageId;
    private List<String> myUrls;

    public MyAdapter(Activity context, List<Book> books, Integer[] imageId, String[] myUrls){
        super(context, R.layout.list_single, books);
        this.context = context;
        this.books = books;
        this.imageId = imageId;
        this.myUrls = Arrays.asList(myUrls);
    }


    public MyAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {



        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(books.get(position).getTitle());

        imageView.setImageResource(imageId[position]);
        Button del_btn = (Button) rowView.findViewById(R.id.deleteBtn);
        final Book item = (Book) getItem(position);
        final int index = position;
        del_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "You Clicked delete ", Toast.LENGTH_SHORT).show();
                pressDeleteBtn(parent.getContext(), item, index);


            }
        });

        String url = myUrls.get(position);

        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.loading_spinner)
                .crossFade()
                .into(imageView);

        return rowView;




    }

    @Override
    public int getCount() {
        return this.books.size();
    }

    private void pressDeleteBtn(Context context, Book item, int index){
        BooksReaderHelper db = new BooksReaderHelper(context);

        db.deleteBook(item);
        this.books.remove(index);
        this.notifyDataSetChanged();

    }
}
