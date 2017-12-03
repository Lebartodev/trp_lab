package main.java.model.data.request;

import main.java.ActionData;

import java.io.Serializable;

public class RequestDeleteMovie implements ActionData, Serializable{
    private int movieId;

    public RequestDeleteMovie(int movieId) {
        this.movieId = movieId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
