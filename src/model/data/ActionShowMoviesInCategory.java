package model.data;

import base.ActionData;
import model.CategoryItem;
import model.MovieItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pavel on 27.09.17.
 */
public class ActionShowMoviesInCategory implements ActionData{

    List<MovieItem> movies = new ArrayList<>();
    String categoryName;

    public ActionShowMoviesInCategory(String categoryName, List<MovieItem> movies){
        this.categoryName = categoryName;
        this.movies = movies;
    }

    public List<MovieItem> getMoviesInCategory(){
        return movies;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
