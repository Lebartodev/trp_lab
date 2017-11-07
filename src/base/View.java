package base;

import io.reactivex.disposables.Disposable;
import model.data.response.ResponseOnCreateMovie;
import model.data.response.OnCategoryEdited;
import model.data.response.UpdateCategories;
import model.data.response.UpdateMovies;

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
                if (actionData instanceof UpdateCategories) {
                    onShowCategories((UpdateCategories) actionData);
                } else if (actionData instanceof UpdateMovies) {
                    onShowSingleCategory((UpdateMovies) actionData);
                } else if (actionData instanceof ActionShowMovie) {
                    onShowMovie((ActionShowMovie) actionData);
                } else if (actionData instanceof ResponseOnCreateMovie) {
                    onCreateMovie((ResponseOnCreateMovie) actionData);
                } else if (actionData instanceof OnCategoryEdited) {
                    onEditCategory((OnCategoryEdited) actionData);
                }

            }, Throwable::printStackTrace);
        }
    }

    public void onEditCategory(OnCategoryEdited data) {

    }

    public void onShowCategories(UpdateCategories data) {

    }

    public void onCreateMovie(ResponseOnCreateMovie data) {

    }

    public void onShowMovie(ActionShowMovie data) {

    }

    public void onShowSingleCategory(UpdateMovies data) {

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
