package main.java.model.data.response;


import main.java.ActionData;
import main.java.model.MovieItem;

import java.io.Serializable;

public class ResponseShowMovie implements ActionData,Serializable {
    private MovieItem movie;

    public ResponseShowMovie(MovieItem movie) {
        this.movie = movie;
    }

    public MovieItem getMovie() {
        return movie;
    }
}
