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

}
