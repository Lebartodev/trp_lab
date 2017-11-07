package model.data.response;

import base.ActionData;
import model.MovieItem;

/**
 * Created by pavel on 27.09.17.
 */
public class ResponseOnCreateMovie implements ActionData {

    private MovieItem movie;

    public ResponseOnCreateMovie(MovieItem movie) {
        this.movie = movie;
    }

    public MovieItem getMovie() {
        return movie;
    }
}
