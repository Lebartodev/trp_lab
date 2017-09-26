package base;

public abstract class Controller<M extends Model> {

    private M model;

    protected final void setModel(M model) {
        this.model = model;
    }
}
