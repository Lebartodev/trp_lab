package model.data;

import base.ActionData;

/**
 * Created by pavel on 29.09.17.
 */
public class ActionOnEditCategory implements ActionData{
    String categoryName;

    public ActionOnEditCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
