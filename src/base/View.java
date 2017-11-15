package base;

import io.reactivex.disposables.Disposable;
import model.data.response.*;

import javax.swing.*;

public abstract class View<M extends Model, C extends Controller> {

    private M model;
    private C controller;
    private Disposable subscriptionModel;


    protected final void setModel(final M model) {
        if (this.model == null) {
            this.model = model;
        }

        subscribeOnModel();

        if (this.controller != null) {
            this.controller.setModel(model);
        }
    }

    protected final M model() {
        return this.model;
    }

    public void unsubscribe() {
        if (subscriptionModel != null)
            subscriptionModel.dispose();
    }

    private void subscribeOnModel() {
        if (model != null) {
            subscriptionModel = model.getPublisher().subscribe(actionData -> {
                if (actionData instanceof ResponseShowCategories) {
                    onShowCategories((ResponseShowCategories) actionData);
                } else if (actionData instanceof ResponseShowMovieList) {
                    onShowSingleCategory((ResponseShowMovieList) actionData);
                } else if (actionData instanceof ResponseShowMovie) {
                    onShowMovie((ResponseShowMovie) actionData);
                } else if (actionData instanceof ResponseOnCreateMovie) {
                    onCreateMovie((ResponseOnCreateMovie) actionData);
                } else if (actionData instanceof OnCategoryEdited) {
                    onEditCategory((OnCategoryEdited) actionData);
                } else if (actionData instanceof ResponseOnCreateCategory) {
                    onCreateCategory((ResponseOnCreateCategory) actionData);
                } else if (actionData instanceof ResponseMovieEditedData) {
                    onShowCategoriesForEdit((ResponseMovieEditedData) actionData);
                }

            }, Throwable::printStackTrace);
        }
    }

    private void onCreateCategory(ResponseOnCreateCategory actionData) {

    }

    public void onEditCategory(OnCategoryEdited data) {

    }

    public void onShowCategories(ResponseShowCategories data) {

    }

    public void onShowCategoriesForEdit(ResponseMovieEditedData data) {

    }

    public void onCreateMovie(ResponseOnCreateMovie data) {

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
        if (this.controller == null)
            this.controller = controller;

        if (this.model != null) {
            try {
                this.controller.setModel(this.model);
            } catch (IllegalStateException ex) {
                System.out.println();
            }
        }
    }
}
