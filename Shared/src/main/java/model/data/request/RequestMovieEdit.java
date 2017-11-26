package main.java.model.data.request;

import main.java.ActionData;
import main.java.model.MovieItem;

/**
 * Created by pavel on 27.09.17.
 */
public class RequestMovieEdit implements ActionData {

    private MovieItem category;

    public RequestMovieEdit(MovieItem category) {
        this.category = category;
    }

    public MovieItem getCategory() {
        return category;
    }
}
