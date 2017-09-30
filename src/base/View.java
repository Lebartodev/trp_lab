package base;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import model.data.*;

import javax.swing.*;

public abstract class View<M extends Model, C extends Controller> {

    private M model;
    private C controller;
    private Disposable subscriptionModel;


    protected final void setModel(final M model) {
        if (this.model != null) {
            throw new IllegalStateException("A Model has already been set on the View.");
        }
        this.model = model;
        subscribeOnModel();

        if (this.controller != null) {
            try {
                this.controller.setModel(model);

            } catch (IllegalStateException ex) {

            }
        }
    }

    protected final M model() {
        return this.model;
    }

    private void subscribeOnModel() {
        if (model != null) {
            subscriptionModel = model.getPublisher().subscribe(actionData -> {
                if (actionData instanceof ActionShowCategories) {
                    onShowCategories((ActionShowCategories) actionData);
                }
                if (actionData instanceof ActionShowMoviesInCategory) {
                    onShowSingleCategory((ActionShowMoviesInCategory) actionData);
                }
                if (actionData instanceof ActionShowMovie) {
                    onShowMovie((ActionShowMovie) actionData);
                }
                if (actionData instanceof ActionOnCreateMovie) {
                    onCreateMovie((ActionOnCreateMovie) actionData);
                }
                if (actionData instanceof ActionOnEditCategory) {
                    onEditCategory((ActionOnEditCategory) actionData);
                }

            }, throwable -> throwable.printStackTrace());
        }
    }

    public void onEditCategory(ActionOnEditCategory data) {

    }

    public void onShowCategories(ActionShowCategories data) {

    }

    public void onCreateMovie(ActionOnCreateMovie data) {

    }

    public void onShowMovie(ActionShowMovie data) {

    }

    public void onShowSingleCategory(ActionShowMoviesInCategory data) {

    }

    abstract public JComponent render();

    protected final C controller() {
        return this.controller;
    }

    protected final void controller(final C controller) {
        if (this.controller != null) {
            throw new IllegalStateException("A Controller has already been set on the View.");
        }

        this.controller = controller;

        if (this.model != null) {
            try {
                this.controller.setModel(this.model);
            } catch (IllegalStateException ex) {

            }
        }
    }
}
