package main.java.base;

public abstract class Controller<M extends Model, V extends View> {

    private M model;
    private V view;

    public final void setModel(M model) {
        this.model = model;
    }

    public final void setView(V view) {
        this.view = view;
    }

    protected M model() {
        return model;
    }
    protected V view() {
        return view;
    }
}
