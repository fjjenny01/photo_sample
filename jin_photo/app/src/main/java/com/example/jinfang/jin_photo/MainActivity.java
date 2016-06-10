package com.example.jinfang.jin_photo;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {
    MyAdapter mAdapter;
    BooksReaderHelper mDbHelper;
    ListView list;

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

//    ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ListView list;

        String[] books={
                "Wuthering Heights",
                "Jane Eyre",
                "The Road Not Taken",
                "God Father",
                "2666",
                "All about Love",
                "Desert Solitaire",
                "Disgrace",
                "Geek Love",
                "Lolita"
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
        };
        initData(books, imageId);
//        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.list);


        // Create a progress bar to display while the list loads
//        ProgressBar progressBar = new ProgressBar(this);
//        progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
//                LayoutParams.WRAP_CONTENT, Gravity.CENTER));
//        progressBar.setIndeterminate(true);
//        getListView().setEmptyView(progressBar);
//
//        // Must add the progress bar to the root of the layout
//        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
//        root.addView(progressBar);
//
//        // For the cursor adapter, specify which columns go into which views
//        String[] fromColumns = {ContactsContract.Data.DISPLAY_NAME};
//        int[] toViews = {android.R.id.text1}; // The TextView in simple_list_item_1

        // Create an empty adapter we will use to display the loaded data.
        // We pass null for the cursor, then update it in onLoadFinished()
//        mAdapter = new SimpleCursorAdapter(this,
//                R.layout.list_single, null,
//                fromColumns, toViews, 0);
        mAdapter = new MyAdapter(MainActivity.this, books, imageId);
        list = (ListView) findViewById(R.id.list);

        list.setAdapter(mAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "You Clicked ", Toast.LENGTH_SHORT).show();
            }
        });
//        list.setOnItemClickListener;
        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
//        getLoaderManager().initLoader(0, null, this);


    }


    private void initData(String[] books, Integer[] imageId) {
        /*CURD operations*/
        BooksReaderHelper db = new BooksReaderHelper(this);
        for (int i =0; i < 10; i++){
             db.addBook(new Book(i+1, books[i], "1"));
        }

    }

}
