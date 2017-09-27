package model.data;

import base.ActionData;
import model.CategoryItem;
import model.MovieItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pavel on 27.09.17.
 */
public class ActionOnCreateMovie implements ActionData{

    List<CategoryItem> categories = new ArrayList<>();
    int currentCategory;
    List<MovieItem> movies = new ArrayList<>();
    int currentMovie;

    public ActionOnCreateMovie(List<CategoryItem> categories, int currentCategory
            , List<MovieItem> movies, int currentMovie) {
        this.categories = categories;
        this.currentCategory = currentCategory;
        this.movies = movies;
        this.currentMovie = currentMovie;
    }

    public List<CategoryItem> getCategories() {
        return categories;
    }

    public int getCurrentCategory() {
        return currentCategory;
    }

    public List<MovieItem> getMovies() {
        return movies;
    }

    public int getCurrentMovie() {
        return currentMovie;
    }
}
