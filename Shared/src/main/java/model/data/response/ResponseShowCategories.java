package main.java.model.data.response;

import main.java.ActionData;
import main.java.model.CategoryItem;

import java.util.ArrayList;
import java.util.List;

public class ResponseShowCategories implements ActionData {
    List<CategoryItem> categories = new ArrayList<>();

    public ResponseShowCategories(List<CategoryItem> categories) {
        this.categories = categories;
    }

    public List<CategoryItem> getCategories() {
        return categories;
    }
}
