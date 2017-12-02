package main.java.model.data.request;

import main.java.ActionData;

import java.io.Serializable;

public class RequestCreateCategory implements Serializable, ActionData {
    private String categoryName;

    public RequestCreateCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
