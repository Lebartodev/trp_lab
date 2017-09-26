package model.data;

import model.CategoryItem;

import java.util.ArrayList;
import java.util.List;

public class ActionShowCategories {


    List<CategoryItem> categories = new ArrayList<CategoryItem>();

    public ActionShowCategories(List<CategoryItem> categories) {
        this.categories = categories;
    }

    public List<CategoryItem> getCategories() {
        return categories;
    }

}
