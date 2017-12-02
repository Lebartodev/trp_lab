package main.java.controller;

import io.reactivex.disposables.Disposable;
import main.java.ClientModel;
import main.java.base.Controller;
import main.java.base.View;
import main.java.model.data.request.*;
import main.java.model.data.response.*;
import main.java.view.CategoriesView;
import org.junit.platform.commons.util.StringUtils;

public class MainController extends Controller<ClientModel, View> {

    private Disposable subscriptionModel;

    public void requestCategories() {
        this.model().send(new RequestShowCategories()).subscribe();
    }

    public void requestCategory(int id) {
        this.model().send(new RequestShowMovieList(id)).subscribe();
    }

    public void requestMovie(int id) {
        this.model().send(new RequestShowMovie(id)).subscribe();
    }

    public void createCategory(String categoryName) {
        this.model().send(new RequestCreateCategory(categoryName)).subscribe();

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
        this.model().send(new RequestStartCategoryEdit(id)).subscribe(actiondata -> {
            if (actiondata instanceof ResponseStartCategoryEdit)
                view().onEditCategory((ResponseStartCategoryEdit) actiondata);
            else if (actiondata instanceof ResponseException) {
                view().onEditCategoryError((ResponseException) actiondata);
            }
        });
    }

    public void deleteMovie(int id) {
        // this.model().deleteMovie(id);
    }

    public void deleteCategory(int id) {
        // this.model().deleteCategory(id);
    }

    protected void subscribeOnModel() {
        if (model() != null) {
            subscriptionModel = model().getPublisher().subscribe(actionData -> {
                if (actionData instanceof ResponseShowCategories) {
                    view().onShowCategories((ResponseShowCategories) actionData);
                } else if (actionData instanceof ResponseShowMovieList) {
                    view().onShowSingleCategory((ResponseShowMovieList) actionData);
                } else if (actionData instanceof ResponseShowMovie) {
                    view().onShowMovie((ResponseShowMovie) actionData);
                }

            }, Throwable::printStackTrace);
        }
    }

    @Override
    public void close() {
        model().send(new RequestExit());
    }
}
