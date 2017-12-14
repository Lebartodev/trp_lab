package main.java.model.data.response;

import main.java.model.MovieItem;

import java.io.Serializable;

public class ResponseShowMovie {
    private MovieItem movie;

    public ResponseShowMovie(MovieItem movie) {
        this.movie = movie;
    }

    public MovieItem getMovie() {
        return movie;
    }
}
