package main.java.model.data.response;

import main.java.ActionData;
import main.java.model.MovieItem;

/**
 * Created by pavel on 29.09.17.
 */
public class OnMovieEdited implements ActionData {
    private MovieItem movie;

    public OnMovieEdited(MovieItem movie) {
        this.movie = movie;
    }

    public MovieItem getMovie() {
        return movie;
    }
}
