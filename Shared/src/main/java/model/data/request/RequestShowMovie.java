package main.java.model.data.request;


import main.java.ActionData;

import java.io.Serializable;

public class RequestShowMovie implements ActionData, Serializable {
    public RequestShowMovie(int movieId) {
        this.movieId = movieId;
    }

    private int movieId;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
