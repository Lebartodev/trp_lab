package base;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

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

        if (this.controller != null) {
            try {
                this.controller.setModel(model);

            } catch (IllegalStateException ex) {

            }
        }
    }

    private void subscribeOnModel() {
        if (model != null) {
            subscriptionModel = model.getPublisher().subscribe(new Consumer<ActionData>() {
                public void accept(ActionData actionData) throws Exception {

                }
            });
        }
    }

    abstract public JComponent render();

    protected final void controller(final C controller) {
        if (this.controller != null) {
            throw new IllegalStateException("A Controller has already been set on the View.");
        }

        this.controller = controller;
        this.controller.setView(this);

        if (this.model != null) {
            try {
                this.controller.setModel(this.model);
            } catch (IllegalStateException ex) {

            }
        }
    }
}
