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
    CategoryItem currentCategory;
    List<MovieItem> movies = new ArrayList<>();
    MovieItem currentMovie;

    public ActionOnCreateMovie(List<CategoryItem> categories, CategoryItem currentCategory
            , List<MovieItem> movies, MovieItem currentMovie) {
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

    public List<MovieItem> getMovies() {
        return movies;
    }

    public MovieItem getCurrentMovie() {
        return currentMovie;
    }
}
