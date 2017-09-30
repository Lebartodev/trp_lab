package controller;

import base.Controller;
import model.MainModel;

public class MainController extends Controller<MainModel> {


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
        this.model().onCreateMovie(name, year, description, genreId, budget);
    }

    public void editMovie(int id, String name, int year, String description, int genreId, int budget) {
        this.model().editMovie(id, name, year, description, genreId, budget);
    }

    public void editCategory(int id, String name) {
        this.model().editCategory(id, name);
    }

    public void requestCategoryForEdit(int id) {
        this.model().onEditCategory(id);
    }

    public void deleteMovie(int id) {
//
    }

    public void deleteCategory(int id) {
        this.model().deleteCategory(id);
    }
}
