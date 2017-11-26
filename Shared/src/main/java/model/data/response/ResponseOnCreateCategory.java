package main.java.model.data.response;

import main.java.ActionData;
import main.java.model.CategoryItem;

/**
 * Created by pavel on 27.09.17.
 */
public class ResponseOnCreateCategory implements ActionData {
    CategoryItem category;

    public ResponseOnCreateCategory(CategoryItem category) {
        this.category = category;
    }

    public CategoryItem getCategory() {
        return category;
    }
}
