package base;


import model.data.response.*;

import javax.swing.*;

public abstract class View<C extends Controller> {

    private C controller;


    public void onEditCategory(ResponseStartCategoryEdit data) {

    }

    public void onShowCategories(ResponseShowCategories data) {

    }


    public void onShowMovie(ResponseShowMovie data) {

    }

    public void onShowSingleCategory(ResponseShowMovieList data) {

    }

    public  void openCategoryEditor(ResponseStartCategoryEdit data){

    };

    public void onError(ResponseException data) {

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

    public void openMovieEditor(ResponseStartMovieEdit actiondata) {

    }

    public void onServerNotStartedException() {

    }
}
