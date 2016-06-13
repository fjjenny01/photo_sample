package com.example.jinfang.jin_photo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jinfang on 6/9/16.
 */
public class BooksReaderHelper extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BooksReader.db";
    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ",";
//    public static final String SQL_CREATE_ENTRY = "CREATE TABLE" + BooksReaderContract.BookEntry.TABLE_NAME + "( id INTEGER PRIMARY KEY AUTOINCREMENT, "
//            + BooksReaderContract.BookEntry.COLUMN_NAME_TITLE
//    + TEXT_TYPE +  COMMA_SEP + BooksReaderContract.BookEntry.COLUMN_NAME_IMAGE_ID + TEXT_TYPE + ")";
    public static final String SQL_CREATE_ENTRY = "CREATE TABLE books (" + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "title TEXT, imageId TEXT )";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + BooksReaderContract.BookEntry.TABLE_NAME;

    public BooksReaderHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    public BooksReaderHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRY);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

//---------------------------------------------------
    /*CURD operations */

    public static final String KEY_ID = "id";
    public void addBook(Book book) {
        Log.d("addBook", book.toString());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BooksReaderContract.BookEntry.COLUMN_NAME_TITLE, book.getTitle() );
        values.put(BooksReaderContract.BookEntry.COLUMN_NAME_IMAGE_ID, book.getImageId());

        db.insert(
                BooksReaderContract.BookEntry.TABLE_NAME,
                null,
                values);
        db.close();
    }

    public void deleteBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(
                BooksReaderContract.BookEntry.TABLE_NAME,
                KEY_ID+" = ?",
                new String[] {String.valueOf(book.getId())});
        db.close();
        Log.d("deleteBook", book.toString());
    }

    public int getBook(int id) {
        return 0;
    }

    public int updateBook(Book book){
        return 0;
    }


    // Get All Books
    public List<Book> getAllBooks() {
        List<Book> books = new LinkedList<Book>();

        String query = "SELECT  * FROM " + BooksReaderContract.BookEntry.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Book book = null;
        if (cursor.moveToFirst()) {
            do {
                book = new Book();
                book.setId(Integer.parseInt(cursor.getString(0)));
                book.setTitle(cursor.getString(1));
                book.setImageId(cursor.getString(2));

                books.add(book);
            } while (cursor.moveToNext());
        }

        Log.d("getAllBooks()", books.toString());

        return books;
    }


}
