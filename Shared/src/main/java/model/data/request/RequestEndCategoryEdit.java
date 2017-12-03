package main.java.model.data.request;

import main.java.ActionData;

import java.io.Serializable;

public class RequestEndCategoryEdit implements ActionData, Serializable {

    private int categoryId = Integer.MIN_VALUE;
    private String categoryName;

    public RequestEndCategoryEdit(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
