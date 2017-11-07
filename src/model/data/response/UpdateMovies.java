package model.data.response;

import base.ActionData;
import model.CategoryItem;
import model.MovieItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by pavel on 27.09.17.
 */
public class UpdateMovies implements ActionData {

    ConcurrentLinkedQueue<MovieItem> movies = new ConcurrentLinkedQueue<>();

    public UpdateMovies(ConcurrentLinkedQueue<MovieItem> movies) {
        this.movies = movies;
    }

    public ConcurrentLinkedQueue<MovieItem> getMovies() {
        return movies;
    }
}
