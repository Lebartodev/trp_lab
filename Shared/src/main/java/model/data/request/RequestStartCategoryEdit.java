package main.java.model.data.request;

import main.java.ActionData;

import java.io.Serializable;

public class RequestStartCategoryEdit implements ActionData, Serializable {

    private int categoryId;

    public RequestStartCategoryEdit(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
