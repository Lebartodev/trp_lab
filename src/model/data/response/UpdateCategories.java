package model.data.response;

import base.ActionData;
import model.CategoryItem;

import java.util.ArrayList;
import java.util.List;

public class UpdateCategories implements ActionData{

    List<CategoryItem> categories = new ArrayList<>();

    public UpdateCategories(List<CategoryItem> categories) {
        this.categories = categories;
    }

    public List<CategoryItem> getCategories() {
        return categories;
    }

}
