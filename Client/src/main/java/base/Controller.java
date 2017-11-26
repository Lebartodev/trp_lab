package main.java.base;

import main.java.model.Model;

public abstract class Controller<M extends Model> {

    private M model;

    protected final void setModel(M model) {
        this.model = model;
    }
    protected M model(){
        return model;
    }
}
