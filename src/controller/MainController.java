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
    public void createCategory(String categoryName ){
        this.model().createCategory(categoryName);
    }

}
