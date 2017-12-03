package main.java.controller;

import io.reactivex.disposables.Disposable;
import main.java.ClientModel;
import main.java.base.Controller;
import main.java.base.View;
import main.java.model.data.request.*;
import main.java.model.data.response.*;

public class MainController extends Controller<ClientModel, View> {

    private Disposable subscriptionModel;

    public void requestCategories() {
        this.model().send(new RequestShowCategories()).subscribe(actionData->{

        },throwable -> {
            view().onServerNotStartedException();
        });
    }

    public void requestCategory(int id) {
        this.model().send(new RequestShowMovieList(id)).subscribe();
    }

    public void requestMovie(int id) {
        this.model().send(new RequestShowMovie(id)).subscribe();
    }

    public void createMovie(String name, int year, String description, int genreId, int budget) {
        this.model().send(new RequestCreateMovie(name, year, description, genreId, budget)).subscribe();
    }


    public void createCategory(String categoryName) {
        this.model().send(new RequestCreateCategory(categoryName)).subscribe();
    }


    public void requestCategoryForEdit(int id) {
        this.model().send(new RequestStartCategoryEdit(id)).subscribe(actiondata -> {
            if (actiondata instanceof ResponseStartCategoryEdit)
                view().openCategoryEditor((ResponseStartCategoryEdit) actiondata);
            else if (actiondata instanceof ResponseException) {
                view().onError((ResponseException) actiondata);
            }
        });
    }

    public void requestMovieForCreate() {
        this.model().send(new RequestStartCreateMovie()).subscribe(actiondata -> {
            if (actiondata instanceof ResponseStartMovieEdit)
                view().openMovieEditor((ResponseStartMovieEdit) actiondata);
            else if (actiondata instanceof ResponseException) {
                view().onError((ResponseException) actiondata);
            }
        });

    }

    public void editCategory(int id, String name) {
        this.model().send(new RequestEndCategoryEdit(id, name)).subscribe();
    }

    public void requestMovieForEdit(int id) {
        this.model().send(new RequestStartMovieEdit(id)).subscribe(actiondata -> {
            if (actiondata instanceof ResponseStartMovieEdit)
                view().openMovieEditor((ResponseStartMovieEdit) actiondata);
            else if (actiondata instanceof ResponseException) {
                view().onError((ResponseException) actiondata);
            }
        });

    }

    public void closeEditCategory(int id) {
        this.model().send(new RequestEndCategoryEdit(id, null)).subscribe();
    }

    public void closeEditMovie(int id) {
        this.model().send(new RequestEndMovieEdit(id, null, 0, null, 0, 0)).subscribe();
    }


    public void editMovie(int id, String name, int year, String description, int genreId, int budget) {
        this.model().send(new RequestEndMovieEdit(id, name, year, description, genreId, budget)).subscribe();
    }


    public void deleteMovie(int id) {
        this.model().send(new RequestDeleteMovie(id)).subscribe(actionData -> {
            if (actionData instanceof ResponseException) {
                view().onError((ResponseException) actionData);
            }
        });
    }

    public void deleteCategory(int id) {
        this.model().send(new RequestDeleteCategory(id)).subscribe(actionData -> {
            if (actionData instanceof ResponseException) {
                view().onError((ResponseException) actionData);
            }
        });
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
