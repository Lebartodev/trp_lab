package model.data.response;

import base.ActionData;
import model.MovieItem;

public class ResponseShowMovie implements ActionData {
    private MovieItem movie;

    public ResponseShowMovie(MovieItem movie) {
        this.movie = movie;
    }

    public MovieItem getMovie() {
        return movie;
    }
}
