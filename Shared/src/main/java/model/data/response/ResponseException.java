package model.data.response;


public class ResponseException  {
    private Exception exception;

    public ResponseException(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
}
