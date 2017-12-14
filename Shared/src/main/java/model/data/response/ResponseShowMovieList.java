package main.java.model.data.response;

import main.java.model.CategoryItem;
import main.java.model.MovieItem;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ResponseShowMovieList  {
    private CategoryItem categoryItem;

    private List<MovieItem> movies = new LinkedList<>();

    private CategoryItem oldCategoryItem;

    public ResponseShowMovieList(List<MovieItem> movies, CategoryItem categoryItem) {
        this.categoryItem = categoryItem;
        this.movies = movies;
    }

    public ResponseShowMovieList(CategoryItem categoryItem, List<MovieItem> movies, CategoryItem oldCategoryItem) {
        this.categoryItem = categoryItem;
        this.movies = movies;
        this.oldCategoryItem = oldCategoryItem;
    }

    public List<MovieItem> getMovies() {
        return movies;
    }

    public CategoryItem getCategoryItem() {
        return categoryItem;
    }

    public void setCategoryItem(CategoryItem categoryItem) {
        this.categoryItem = categoryItem;
    }

    public void setMovies(List<MovieItem> movies) {
        this.movies = movies;
    }

    public CategoryItem getOldCategoryItem() {
        return oldCategoryItem;
    }

    public void setOldCategoryItem(CategoryItem oldCategoryItem) {
        this.oldCategoryItem = oldCategoryItem;
    }
}
