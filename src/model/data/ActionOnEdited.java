package model.data;

import base.ActionData;
import model.CategoryItem;
import model.MovieItem;

/**
 * Created by pavel on 27.09.17.
 */
public class ActionOnEdited implements ActionData {

    private MovieItem movie;
    private CategoryItem category;

    public ActionOnEdited(MovieItem movie) {
        this.movie = movie;
    }

    public ActionOnEdited(CategoryItem category) {
        this.category = category;
    }

    public MovieItem getMovie() {
        return movie;
    }

    public CategoryItem getCategory() {
        return category;
    }
}
