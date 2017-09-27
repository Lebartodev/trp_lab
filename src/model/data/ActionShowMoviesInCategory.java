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

    CategoryItem category = new CategoryItem();

    public ActionShowMoviesInCategory(CategoryItem category){
        this.category = category;
    }

    public CategoryItem getMoviesInCategory(){
        return category;
    }

}
