package base;

public abstract class Controller<M extends Model, V extends View> {

    private M model;
    private V view;
}
