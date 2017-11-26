package main.java.model.data.request;

import main.java.model.CategoryItem;

public class RequestUpdateMovies {
    private CategoryItem categoryItem;

    public RequestUpdateMovies(CategoryItem categoryItem) {
        this.categoryItem = categoryItem;
    }

    public CategoryItem getCategoryItem() {
        return categoryItem;
    }
}
