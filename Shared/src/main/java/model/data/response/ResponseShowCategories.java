package model.data.response;

import model.CategoryItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResponseShowCategories {
    private List<CategoryItem> categories = new ArrayList<>();

    public ResponseShowCategories(List<CategoryItem> categories) {
        this.categories = categories;
    }

    public List<CategoryItem> getCategories() {
        return categories;
    }
}
