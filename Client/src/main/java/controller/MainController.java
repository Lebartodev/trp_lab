package controller;

import base.Controller;
import base.Model;
import base.View;
import io.reactivex.disposables.Disposable;
import model.data.request.*;
import util.MarshallerUtil;

public class MainController extends Controller<Model, View> {
    private Disposable subscriptionModel;

    public void requestCategories() {
        this.model().send(MarshallerUtil.marshallAction(new RequestShowCategories(), RequestShowCategories.class)).subscribe(actionData -> {

        }, throwable -> {
            view().onServerNotStartedException();
        });
    }

    public void requestCategory(int id) {
        this.model().send(MarshallerUtil.marshallAction(new RequestShowMovieList(id), RequestShowMovieList.class)).subscribe();
    }

    public void requestMovie(int id) {
        this.model().send(MarshallerUtil.marshallAction(new RequestShowMovie(id), RequestShowMovie.class)).subscribe();
    }

    public void createMovie(String name, int year, String description, int genreId, int budget) {
        this.model().send(MarshallerUtil.marshallAction(new RequestCreateMovie(name, year, description, genreId, budget), RequestCreateMovie.class)).subscribe();
    }


    public void createCategory(String categoryName) {
        this.model().send(MarshallerUtil.marshallAction(new RequestCreateCategory(categoryName), RequestCreateCategory.class)).subscribe();
    }


    public void requestCategoryForEdit(int id) {
        this.model().send(MarshallerUtil.marshallAction(new RequestStartCategoryEdit(id), RequestStartCategoryEdit.class)).subscribe(actiondata -> {
//            if (actiondata instanceof ResponseStartCategoryEdit)
//                view().openCategoryEditor((ResponseStartCategoryEdit) actiondata);
//            else if (actiondata instanceof ResponseException) {
//                view().onError((ResponseException) actiondata);
//            }
        });
    }

    public void requestMovieForCreate() {
        this.model().send(MarshallerUtil.marshallAction(new RequestStartCreateMovie(), RequestStartCreateMovie.class)).subscribe(actiondata -> {
//            if (actiondata instanceof ResponseStartMovieEdit)
//                view().openMovieEditor((ResponseStartMovieEdit) actiondata);
//            else if (actiondata instanceof ResponseException) {
//                view().onError((ResponseException) actiondata);
//            }
        });

    }

    public void editCategory(int id, String name) {
        this.model().send(MarshallerUtil.marshallAction(new RequestEndCategoryEdit(id, name), RequestEndCategoryEdit.class)).subscribe();
    }

    public void requestMovieForEdit(int id) {
        this.model().send(MarshallerUtil.marshallAction(new RequestStartMovieEdit(id), RequestStartMovieEdit.class)).subscribe(actiondata -> {
//            if (actiondata instanceof ResponseStartMovieEdit)
//                view().openMovieEditor((ResponseStartMovieEdit) actiondata);
//            else if (actiondata instanceof ResponseException) {
//                view().onError((ResponseException) actiondata);
//            }
        });

    }

    public void closeEditCategory(int id) {
        this.model().send(MarshallerUtil.marshallAction(new RequestEndCategoryEdit(id, null), RequestEndCategoryEdit.class)).subscribe();
    }

    public void closeEditMovie(int id) {
        this.model().send(MarshallerUtil.marshallAction(new RequestEndMovieEdit(id, null, 0, null, 0, 0), RequestEndMovieEdit.class)).subscribe();
    }


    public void editMovie(int id, String name, int year, String description, int genreId, int budget) {
        this.model().send(MarshallerUtil.marshallAction(new RequestEndMovieEdit(id, name, year, description, genreId, budget), RequestEndMovieEdit.class)).subscribe();
    }


    public void deleteMovie(int id) {
        this.model().send(MarshallerUtil.marshallAction(new RequestDeleteMovie(id), RequestDeleteMovie.class)).subscribe(actionData -> {
//            if (actionData instanceof ResponseException) {
//                view().onError((ResponseException) actionData);
//            }
        });
    }

    public void deleteCategory(int id) {
        this.model().send(MarshallerUtil.marshallAction(new RequestDeleteCategory(id), RequestDeleteCategory.class)).subscribe(actionData -> {
//            if (actionData instanceof ResponseException) {
//                view().onError((ResponseException) actionData);
//            }
        });
    }

    protected void subscribeOnModel() {
        if (model() != null) {
            subscriptionModel = model().getPublisher().subscribe(actionData -> {
//                if (actionData instanceof ResponseShowCategories) {
//                    view().onShowCategories((ResponseShowCategories) actionData);
//                } else if (actionData instanceof ResponseShowMovieList) {
//                    view().onShowSingleCategory((ResponseShowMovieList) actionData);
//                } else if (actionData instanceof ResponseShowMovie) {
//                    view().onShowMovie((ResponseShowMovie) actionData);
//                }

            }, Throwable::printStackTrace);
        }
    }

    @Override
    public void close() {
        model().send(MarshallerUtil.marshallAction(new RequestExit(), RequestExit.class)).subscribe();
    }


}
