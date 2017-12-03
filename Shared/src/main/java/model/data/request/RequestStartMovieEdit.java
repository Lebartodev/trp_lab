package main.java.model.data.request;

import main.java.ActionData;

import java.io.Serializable;

/**
 * Created by pavel on 27.09.17.
 */
public class RequestStartMovieEdit implements ActionData, Serializable {
    private int movieId;


    public RequestStartMovieEdit(int movieId) {
        this.movieId = movieId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
