package model.data;

import base.ActionData;
import model.CategoryItem;

import java.util.ArrayList;
import java.util.List;

public class ActionShowCategories implements ActionData{

    List<CategoryItem> categories = new ArrayList<>();

    public ActionShowCategories(List<CategoryItem> categories) {
        this.categories = categories;
    }

    public List<CategoryItem> getCategories() {
        return categories;
    }

}
