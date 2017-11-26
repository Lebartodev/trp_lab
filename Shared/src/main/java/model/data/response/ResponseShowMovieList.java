package main.java.model.data.response;

import main.java.ActionData;
import main.java.model.MovieItem;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ResponseShowMovieList implements ActionData {
    ConcurrentLinkedQueue<MovieItem> movies = new ConcurrentLinkedQueue<>();

    public ResponseShowMovieList(ConcurrentLinkedQueue<MovieItem> movies) {
        this.movies = movies;
    }

    public ConcurrentLinkedQueue<MovieItem> getMovies() {
        return movies;
    }
}
