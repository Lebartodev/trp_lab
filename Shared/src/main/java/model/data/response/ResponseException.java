package main.java.model.data.response;

import main.java.ActionData;

import java.io.Serializable;

public class ResponseException implements ActionData, Serializable {
    private Exception exception;

    public ResponseException(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
}
