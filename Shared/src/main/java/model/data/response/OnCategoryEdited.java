package main.java.model.data.response;


import main.java.ActionData;
import main.java.model.CategoryItem;

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
