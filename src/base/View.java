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
                if (actionData instanceof ActionShowCategories) {
                    onShowCategories((ActionShowCategories) actionData);
                } else if (actionData instanceof ActionShowMoviesInCategory) {
                    onShowSingleCategory((ActionShowMoviesInCategory) actionData);
                } else if (actionData instanceof ActionShowMovie) {
                    onShowMovie((ActionShowMovie) actionData);
                } else if (actionData instanceof ActionOnCreateMovie) {
                    onCreateMovie((ActionOnCreateMovie) actionData);
                } else if (actionData instanceof ActionOnEditCategory) {
                    onEditCategory((ActionOnEditCategory) actionData);
                }

            }, Throwable::printStackTrace);
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
