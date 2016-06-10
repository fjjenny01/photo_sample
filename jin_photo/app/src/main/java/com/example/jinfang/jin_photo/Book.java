package com.example.jinfang.jin_photo;

/**
 * Created by jinfang on 6/9/16.
 * might need to changed later
 */
public class Book {
    int id;
    String title;
    String imageId;

    public Book(int id, String title, String imageId) {
        this.id = id;
        this.title = title;
        this.imageId= imageId;
    }
    public Book() {}
    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
