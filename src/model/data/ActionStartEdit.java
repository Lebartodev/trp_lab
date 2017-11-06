package model.data;

import base.ActionData;
import model.CategoryItem;
import model.MovieItem;

/**
 * Created by pavel on 27.09.17.
 */
public class ActionStartEdit implements ActionData {

    private MovieItem movie;
    private CategoryItem category;

    public ActionStartEdit(MovieItem movie) {
        this.movie = movie;
    }

    public ActionStartEdit(CategoryItem category) {
        this.category = category;
    }

    public MovieItem getMovie() {
        return movie;
    }

    public CategoryItem getCategory() {
        return category;
    }
}
