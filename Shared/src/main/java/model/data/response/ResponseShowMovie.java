package model.data.response;

import model.MovieItem;

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
