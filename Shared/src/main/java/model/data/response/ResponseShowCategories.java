package main.java.model.data.response;

import main.java.ActionData;
import main.java.model.CategoryItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResponseShowCategories implements ActionData,Serializable {
    List<CategoryItem> categories = new ArrayList<>();

    public ResponseShowCategories(List<CategoryItem> categories) {
        this.categories = categories;
    }

    public List<CategoryItem> getCategories() {
        return categories;
    }
}
