package main.java.base;


import main.java.model.data.response.*;

import javax.swing.*;

public abstract class View<C extends Controller> {

    private C controller;

    private void onCreateCategory(ResponseCreateCategory actionData) {

    }

    public void onEditCategory(ResponseStartCategoryEdit data) {

    }

    public void onShowCategories(ResponseShowCategories data) {

    }

    public void onShowCategoriesForEdit(ResponseMovieEditedData data) {

    }

    public void onCreateMovie(ResponseCreateMovie data) {

    }

    public void onShowMovie(ResponseShowMovie data) {

    }

    public void onShowSingleCategory(ResponseShowMovieList data) {

    }

    abstract public JComponent render();

    protected final C controller() {
        return this.controller;
    }

    protected final void controller(final C controller) {
        if (this.controller == null) {
            this.controller = controller;
            this.controller.setView(this);

        }
    }
}
