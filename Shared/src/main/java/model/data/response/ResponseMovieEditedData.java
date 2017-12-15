package model.data.response;

import model.CategoryItem;

import java.util.ArrayList;
import java.util.List;

public class ResponseMovieEditedData  {
    List<CategoryItem> categories = new ArrayList<>();

    public ResponseMovieEditedData(List<CategoryItem> categories) {
        this.categories = categories;
    }

    public List<CategoryItem> getCategories() {
        return categories;
    }
}