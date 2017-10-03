package model.data;

import base.ActionData;
import model.CategoryItem;
import model.MovieItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by pavel on 27.09.17.
 */
public class ActionShowMoviesInCategory implements ActionData{

    ConcurrentLinkedQueue<MovieItem> movies = new ConcurrentLinkedQueue<>();
    String categoryName;

    public ActionShowMoviesInCategory(String categoryName
            , ConcurrentLinkedQueue<MovieItem> movies){
        this.categoryName = categoryName;
        this.movies = movies;
    }

    public ConcurrentLinkedQueue<MovieItem> getMoviesInCategory(){
        return movies;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
