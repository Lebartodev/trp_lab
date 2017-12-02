package main.java.model.data.response;

import main.java.ActionData;
import main.java.model.CategoryItem;

import java.io.Serializable;

/**
 * Created by pavel on 27.09.17.
 */
public class ResponseCreateCategory implements ActionData,Serializable {
    CategoryItem category;

    public ResponseCreateCategory(CategoryItem category) {
        this.category = category;
    }

    public CategoryItem getCategory() {
        return category;
    }
}
