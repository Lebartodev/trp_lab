package main.java.model.data.response;


import main.java.model.CategoryItem;

import java.io.Serializable;

/**
 * Created by pavel on 29.09.17.
 */
public class ResponseStartCategoryEdit {
    private CategoryItem category;

    public ResponseStartCategoryEdit(CategoryItem category) {
        this.category = category;
    }

    public CategoryItem getCategory() {
        return category;
    }
}
