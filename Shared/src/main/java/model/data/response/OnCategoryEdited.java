package model.data.response;

import base.ActionData;
import model.CategoryItem;

/**
 * Created by pavel on 29.09.17.
 */
public class OnCategoryEdited implements ActionData {
    private CategoryItem category;

    public OnCategoryEdited(CategoryItem category) {
        this.category = category;
    }

    public CategoryItem getCategory() {
        return category;
    }
}
