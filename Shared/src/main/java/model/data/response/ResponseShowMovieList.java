package main.java.model.data.response;

import main.java.ActionData;
import main.java.model.CategoryItem;
import main.java.model.MovieItem;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ResponseShowMovieList implements ActionData,Serializable {
    private CategoryItem categoryItem;

    private List<MovieItem> movies = new LinkedList<>();

    public ResponseShowMovieList(List<MovieItem> movies, CategoryItem categoryItem) {
        this.categoryItem = categoryItem;
        this.movies = movies;
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
}
