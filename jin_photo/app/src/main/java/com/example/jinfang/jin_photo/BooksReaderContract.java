package com.example.jinfang.jin_photo;

import android.provider.BaseColumns;

/**
 * Created by jinfang on 6/9/16.
 */
public class BooksReaderContract {

    //prevent someone from accidentlky instantiating the contract class,
    //give it an empty constructor
    public BooksReaderContract() {}

    public static abstract  class BookEntry implements BaseColumns {
        public static final String TABLE_NAME = "books";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_IMAGE_ID = "imageId";

    }
}
