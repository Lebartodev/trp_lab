package model.data.response;

import base.ActionData;
import model.CategoryItem;
import model.MovieItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by pavel on 27.09.17.
 */
public class ResponseOnCreateCategory implements ActionData{
    CategoryItem category;

    public ResponseOnCreateCategory(CategoryItem category) {
        this.category = category;
    }

    public CategoryItem getCategory() {
        return category;
    }
}
