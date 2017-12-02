package main.java;

import main.java.model.CategoryItem;
import main.java.model.MovieItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataObject implements Serializable{
    private static String filename = "model.dat";

    private Map<Integer,CategoryItem> categories = new HashMap<Integer, CategoryItem>();

    private Map<Integer,MovieItem> movies = new HashMap<Integer, MovieItem>();

    private int filmId;

    private int categoryId;

    private List<Integer> lockedMovies = new ArrayList<>();

    private List<Integer> lockedCategories = new ArrayList<>();

    DataObject() {
    }

    static String getFilename() {
        return filename;
    }

    static void setFilename(String filename) {
        DataObject.filename = filename;
    }

    Map<Integer, CategoryItem> getCategories() {
        return categories;
    }

    void setCategories(Map<Integer, CategoryItem> categories) {
        this.categories = categories;
    }

    Map<Integer, MovieItem> getMovies() {
        return movies;
    }

    void setMovies(Map<Integer, MovieItem> movies) {
        this.movies = movies;
    }

    synchronized int getFilmId() {
        return ++filmId;
    }

    void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    synchronized int getCategoryId() {
        return ++categoryId;
    }

    void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    List<Integer> getLockedMovies() {
        return lockedMovies;
    }

    void setLockedMovies(List<Integer> lockedMovies) {
        this.lockedMovies = lockedMovies;
    }

    List<Integer> getLockedCategories() {
        return lockedCategories;
    }

    void setLockedCategories(List<Integer> lockedCategories) {
        this.lockedCategories = lockedCategories;
    }

}
