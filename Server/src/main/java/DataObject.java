package main.java;

import main.java.model.CategoryItem;
import main.java.model.MovieItem;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DataObject implements Serializable{
    private static String filename = "model.dat";

    private Map<Integer,CategoryItem> categories = new HashMap<Integer, CategoryItem>();

    private Map<Integer,MovieItem> movies = new HashMap<Integer, MovieItem>();

    private AtomicInteger filmId = new AtomicInteger();

    private AtomicInteger categoryId = new AtomicInteger();

    private List<Integer> lockedMovies = new ArrayList<>();

    private List<Integer> lockedCategories = new ArrayList<>();

    DataObject() {
    }

    static String getFilename() {
        return filename;
    }

    public static void setFilename(String filename) {
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

    AtomicInteger getFilmId() {
        return filmId;
    }

    void setFilmId(AtomicInteger filmId) {
        this.filmId = filmId;
    }

    AtomicInteger getCategoryId() {
        return categoryId;
    }

    void setCategoryId(AtomicInteger categoryId) {
        this.categoryId = categoryId;
    }

    List<Integer> getLockedMovies() {
        return lockedMovies;
    }

    public void setLockedMovies(List<Integer> lockedMovies) {
        this.lockedMovies = lockedMovies;
    }

    public List<Integer> getLockedCategories() {
        return lockedCategories;
    }

    public void setLockedCategories(List<Integer> lockedCategories) {
        this.lockedCategories = lockedCategories;
    }
}
