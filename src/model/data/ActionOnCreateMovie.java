package model.data;

import base.ActionData;
import model.CategoryItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pavel on 27.09.17.
 */
public class ActionOnCreateMovie implements ActionData{

    List<CategoryItem> categories = new ArrayList<CategoryItem>();

    public ActionOnCreateMovie(List<CategoryItem> categories) {
        this.categories = categories;
    }

    public List<CategoryItem> getCategories() {
        return categories;
    }
}
