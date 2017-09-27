package model.data;

import base.ActionData;
import model.MovieItem;

/**
 * Created by pavel on 27.09.17.
 */
public class ActionShowMovie implements ActionData{
    
    MovieItem movie = new MovieItem();

    public ActionShowMovie(MovieItem movie) {
        this.movie = movie;
    }

    public MovieItem getMovie() {
        return movie;
    }
}
