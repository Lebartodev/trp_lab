package model.data;

import base.ActionData;
import model.CategoryItem;
import model.MovieItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by pavel on 27.09.17.
 */
public class ActionOnCreateMovie implements ActionData{

    List<CategoryItem> categories = new ArrayList<>();
    CategoryItem currentCategory;
    ConcurrentLinkedQueue<MovieItem> movies = new ConcurrentLinkedQueue<>();
    MovieItem currentMovie;

    public ActionOnCreateMovie(List<CategoryItem> categories, CategoryItem currentCategory
            , ConcurrentLinkedQueue<MovieItem> movies, MovieItem currentMovie) {
        this.categories = categories;
        this.currentCategory = currentCategory;
        this.movies = movies;
        this.currentMovie = currentMovie;
    }

    public List<CategoryItem> getCategories() {
        return categories;
    }

    public CategoryItem getCurrentCategory() {
        return currentCategory;
    }

    public ConcurrentLinkedQueue<MovieItem> getMovies() {
        return movies;
    }

    public MovieItem getCurrentMovie() {
        return currentMovie;
    }
}
