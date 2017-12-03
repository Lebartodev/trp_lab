package main.java.model.data.request;

import main.java.ActionData;

import java.io.Serializable;

public class RequestDeleteCategory implements ActionData, Serializable {
    private int id;

    public RequestDeleteCategory(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
