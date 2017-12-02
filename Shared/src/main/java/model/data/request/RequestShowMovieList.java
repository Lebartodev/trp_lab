package main.java.model.data.request;

import main.java.ActionData;

import java.io.Serializable;

public class RequestShowMovieList implements ActionData,Serializable{
    public RequestShowMovieList(int categoryId) {
        this.categoryId = categoryId;
    }

    private int categoryId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
