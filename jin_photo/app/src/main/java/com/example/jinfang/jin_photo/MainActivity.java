package com.example.jinfang.jin_photo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    MyAdapter mAdapter;
    final BooksReaderHelper db =  new BooksReaderHelper(this);
    ListView list;
    List<Book> books = new ArrayList<>();

    public static Runnable run;
    public static final String BOOK_TITLE = "book_title";

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolBar);

        String[] book_titles={
                "Wuthering Heights",
                "Jane Eyre",
                "The Road Not Taken",
                "God Father",
                "2666",
                "All about Love",
                "Desert Solitaire",
                "Disgrace",
                "Geek Love",
                "Lolita",
                "The Hitchhiker's Guide to the Galaxy ",
                "If on a Winter's Night a Traveler ",
                "Infinite Jest "

        };
        Integer[] imageId = {
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
        };



        initData(book_titles, imageId);




        String[] urls = new String[] {"http://goo.gl/gEgYUd", "http://goo.gl/gEgYUd", "http://goo.gl/gEgYUd", "http://goo.gl/gEgYUd", "http://goo.gl/gEgYUd",
        "http://goo.gl/gEgYUd", "http://goo.gl/gEgYUd", "http://goo.gl/gEgYUd", "http://goo.gl/gEgYUd", "http://goo.gl/gEgYUd", "http://goo.gl/gEgYUd",
        "http://goo.gl/gEgYUd", "http://goo.gl/gEgYUd"};
        mAdapter = new MyAdapter(MainActivity.this, this.books, imageId, urls);
        list = (ListView) findViewById(R.id.list);

        list.setAdapter(mAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "You Clicked ", Toast.LENGTH_SHORT).show();
                Object entry = parent.getAdapter().getItem(position);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                TextView title = (TextView) findViewById(R.id.txt);
                intent.putExtra(BOOK_TITLE, entry.toString());
                startActivity(intent);
            }
        });


        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState){
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_btn:
                Toast.makeText(MainActivity.this, "You Clicked add", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(MainActivity.this, "You Clicked settings", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void initData(String[] book_titles, Integer[] imageId) {
        /*CURD operations*/
        Log.d("Jin", "init data");

        if (db.getAllBooks().size() == 0){

            for (int i =0; i < 13; i++){
                Book newBook= new Book(i+1, book_titles[i], "http://goo.gl/gEgYUd");
                this.books.add(newBook);
                db.addBook(newBook);
            }

        }else{

            this.books = db.getAllBooks();
        }

    }


}
