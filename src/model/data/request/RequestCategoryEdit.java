package model.data.request;

import base.ActionData;
import model.CategoryItem;

/**
 * Created by pavel on 27.09.17.
 */
public class RequestCategoryEdit implements ActionData {

    private CategoryItem category;

    public RequestCategoryEdit(CategoryItem category) {
        this.category = category;
    }

    public CategoryItem getCategory() {
        return category;
    }
}
