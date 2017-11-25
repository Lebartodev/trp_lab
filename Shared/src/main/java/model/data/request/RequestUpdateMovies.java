package model.data.request;

import model.CategoryItem;

public class RequestUpdateMovies {
    private CategoryItem categoryItem;

    public RequestUpdateMovies(CategoryItem categoryItem) {
        this.categoryItem = categoryItem;
    }

    public CategoryItem getCategoryItem() {
        return categoryItem;
    }
}
