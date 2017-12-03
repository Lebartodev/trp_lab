package main.java.controller;

import main.java.ClientModel;
import main.java.base.Controller;
import main.java.base.View;
import main.java.model.data.request.RequestCreateCategory;
import main.java.model.data.request.RequestEndCategoryEdit;
import main.java.model.data.request.RequestStartCategoryEdit;
import main.java.model.data.response.ResponseException;
import main.java.model.data.response.ResponseStartCategoryEdit;

public class CategoryController extends Controller<ClientModel, View> {

    public void createCategory(String categoryName) {
        this.model().send(new RequestCreateCategory(categoryName)).subscribe();

    }


    public void requestCategoryForEdit(int id) {
        this.model().send(new RequestStartCategoryEdit(id)).subscribe(actiondata -> {
            if (actiondata instanceof ResponseStartCategoryEdit)
                view().onEditCategory((ResponseStartCategoryEdit) actiondata);
            else if (actiondata instanceof ResponseException) {
                view().onError((ResponseException) actiondata);
            }
        });
    }

    public void editCategory(int id, String name) {
        this.model().send(new RequestEndCategoryEdit(id, name)).subscribe();
    }

    public void closeEditCategory(int id) {
        this.model().send(new RequestEndCategoryEdit(id,null)).subscribe();
    }


    @Override
    protected void subscribeOnModel() {

    }

    @Override
    public void close() {

    }
}
