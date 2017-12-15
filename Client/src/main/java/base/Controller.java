package base;

public abstract class Controller<M extends Model, V extends View> {

    private M model;
    private V view;

    public final void setModel(M model) {
        this.model = model;
        subscribeOnModel();
    }

    public final void setView(V view) {
        this.view = view;
    }

    public M model() {
        return model;
    }
    protected V view() {
        return view;
    }

    protected abstract void subscribeOnModel();

    public abstract void close();
}
