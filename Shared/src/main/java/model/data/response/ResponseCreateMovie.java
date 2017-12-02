package main.java.model.data.response;


import main.java.ActionData;
import main.java.model.MovieItem;

/**
 * Created by pavel on 27.09.17.
 */
public class ResponseCreateMovie implements ActionData {

    private MovieItem movie;

    public ResponseCreateMovie(MovieItem movie) {
        this.movie = movie;
    }

    public MovieItem getMovie() {
        return movie;
    }
}
