package base;

public abstract class Controller<M extends Model, V extends View> {

    private M model;
    private V view;

    protected final void setModel(M model) {
        this.model = model;
    }


    protected final void setView(V view) {
        this.view = view;
    }
}
