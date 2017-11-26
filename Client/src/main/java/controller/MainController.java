package main.java.controller;

import main.java.ClientModel;
import main.java.base.Controller;
import org.junit.platform.commons.util.StringUtils;

public class MainController extends Controller<ClientModel> {


    public void requestCategories() {
        this.model().getCategories();
    }

    public void requestCategory(int id) {
        this.model().getMoviesInCategory(id);
    }

    public void requestMovie(int id) {
        this.model().getMovie(id);
    }

    public void createCategory(String categoryName) {
        this.model().createCategory(categoryName);
    }

    public void createMovie(String name, int year, String description, int genreId, int budget) {
        if(StringUtils.isBlank(name)){
            name="New movie";
        }
        if(StringUtils.isBlank(description)){
            description = "Please add description.";
        }
        this.model().onCreateMovie(name, year, description, genreId, budget);
    }

    public void editMovie(int id, String name, int year, String description, int genreId, int budget) {
        if(StringUtils.isBlank(name)){
            name="Edited movie";
        }
        if(StringUtils.isBlank(description)){
            description = "Please add description.";
        }
        this.model().editMovie(id, name, year, description, genreId, budget);
    }

    public void editCategory(int id, String name) {
        this.model().editCategory(id, name);
    }

    public void requestCategoryForEdit(int id) {
        this.model().onEditCategory(id);
    }

    public void deleteMovie(int id) {
        this.model().deleteMovie(id);
    }

    public void deleteCategory(int id) {
        this.model().deleteCategory(id);
    }
}
