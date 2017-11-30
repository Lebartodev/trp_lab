package main.java.model.data.response;

import main.java.ActionData;
import main.java.model.MovieItem;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ResponseShowMovieList implements ActionData {
    List<MovieItem> movies = new LinkedList<>();

    public ResponseShowMovieList(List<MovieItem> movies) {
        this.movies = movies;
    }

    public List<MovieItem> getMovies() {
        return movies;
    }
}
