package main.java;

import main.java.model.CategoryItem;
import main.java.model.MovieItem;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DataObject {
    private static String filename = "model.dat";

    private Map<Integer,CategoryItem> categories = new HashMap<Integer, CategoryItem>();

    private Map<Integer,MovieItem> movies = new HashMap<Integer, MovieItem>();

    private AtomicInteger filmId = new AtomicInteger();

    private AtomicInteger categoryId = new AtomicInteger();

    private List<Integer> lockedMovies = new ArrayList<>();

    private List<Integer> lockedCategories = new ArrayList<>();

    public DataObject() {
    }

    public static String getFilename() {
        return filename;
    }

    public static void setFilename(String filename) {
        DataObject.filename = filename;
    }

    public Map<Integer, CategoryItem> getCategories() {
        return categories;
    }

    public void setCategories(Map<Integer, CategoryItem> categories) {
        this.categories = categories;
    }

    public Map<Integer, MovieItem> getMovies() {
        return movies;
    }

    public void setMovies(Map<Integer, MovieItem> movies) {
        this.movies = movies;
    }

    public AtomicInteger getFilmId() {
        return filmId;
    }

    public void setFilmId(AtomicInteger filmId) {
        this.filmId = filmId;
    }

    public AtomicInteger getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(AtomicInteger categoryId) {
        this.categoryId = categoryId;
    }

    public List<Integer> getLockedMovies() {
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
