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

}
