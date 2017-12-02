package main.java.model.data.request;

import main.java.ActionData;
import main.java.model.CategoryItem;

/**
 * Created by pavel on 27.09.17.
 */
public class RequestStartCategoryEdit implements ActionData {

    private CategoryItem category;

    public RequestStartCategoryEdit(CategoryItem category) {
        this.category = category;
    }

    public CategoryItem getCategory() {
        return category;
    }
}
