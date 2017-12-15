package model.data.response;

import model.CategoryItem;
import model.MovieItem;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pavel on 29.09.17.
 */
public class ResponseStartMovieEdit {
    private MovieItem movie;
    private List<CategoryItem> categories;

    public ResponseStartMovieEdit(MovieItem movie, List<CategoryItem> categories) {
        this.movie = movie;
        this.categories = categories;
    }

    public MovieItem getMovie() {
        return movie;
    }

    public void setMovie(MovieItem movie) {
        this.movie = movie;
    }

    public List<CategoryItem> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryItem> categories) {
        this.categories = categories;
    }
}
