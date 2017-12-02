package main.java.controller;

import io.reactivex.functions.Consumer;
import main.java.ActionData;
import main.java.ClientModel;
import main.java.base.Controller;
import main.java.model.data.request.RequestShowCategories;
import main.java.model.data.request.RequestShowMovie;
import main.java.model.data.request.RequestShowMovieList;
import main.java.model.data.response.ResponseShowCategories;
import main.java.model.data.response.ResponseShowMovie;
import main.java.model.data.response.ResponseShowMovieList;
import main.java.view.CategoriesView;
import org.junit.platform.commons.util.StringUtils;

public class MainController extends Controller<ClientModel, CategoriesView> {


    public void requestCategories() {
        this.model().send(new RequestShowCategories()).subscribe(actionData -> {
            this.view().onShowCategories((ResponseShowCategories) actionData);
        }, Throwable::printStackTrace);
    }

    public void requestCategory(int id) {
        this.model().send(new RequestShowMovieList(id)).subscribe(actionData -> {
            this.view().onShowSingleCategory((ResponseShowMovieList) actionData);
        }, Throwable::printStackTrace);
    }

    public void requestMovie(int id) {
         this.model().send(new RequestShowMovie(id)).subscribe(actionData -> {
             this.view().onShowMovie((ResponseShowMovie) actionData);
         }, Throwable::printStackTrace);
    }

    public void createCategory(String categoryName) {
        //this.model().createCategory(categoryName);
    }

    public void createMovie(String name, int year, String description, int genreId, int budget) {
        if (StringUtils.isBlank(name)) {
            name = "New movie";
        }
        if (StringUtils.isBlank(description)) {
            description = "Please add description.";
        }
        // this.model().onCreateMovie(name, year, description, genreId, budget);
    }

    public void editMovie(int id, String name, int year, String description, int genreId, int budget) {
        if (StringUtils.isBlank(name)) {
            name = "Edited movie";
        }
        if (StringUtils.isBlank(description)) {
            description = "Please add description.";
        }
        //this.model().editMovie(id, name, year, description, genreId, budget);
    }

    public void editCategory(int id, String name) {
        // this.model().editCategory(id, name);
    }

    public void requestCategoryForEdit(int id) {
        // this.model().onEditCategory(id);
    }

    public void deleteMovie(int id) {
        // this.model().deleteMovie(id);
    }

    public void deleteCategory(int id) {
        // this.model().deleteCategory(id);
    }

//    private void subscribeOnModel() {
//        if (model != null) {
//            subscriptionModel = model.getPublisher().subscribe(actionData -> {
//                if (actionData instanceof ResponseShowCategories) {
//                    onShowCategories((ResponseShowCategories) actionData);
//                } else if (actionData instanceof ResponseShowMovieList) {
//                    onShowSingleCategory((ResponseShowMovieList) actionData);
//                } else if (actionData instanceof ResponseShowMovie) {
//                    onShowMovie((ResponseShowMovie) actionData);
//                } else if (actionData instanceof ResponseOnCreateMovie) {
//                    onCreateMovie((ResponseOnCreateMovie) actionData);
//                } else if (actionData instanceof OnCategoryEdited) {
//                    onEditCategory((OnCategoryEdited) actionData);
//                } else if (actionData instanceof ResponseOnCreateCategory) {
//                    onCreateCategory((ResponseOnCreateCategory) actionData);
//                } else if (actionData instanceof ResponseMovieEditedData) {
//                    onShowCategoriesForEdit((ResponseMovieEditedData) actionData);
//                }
//
//            }, Throwable::printStackTrace);
//        }
//    }
}
