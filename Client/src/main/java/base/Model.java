package main.java.base;

import io.reactivex.subjects.PublishSubject;
import main.java.ActionData;

public abstract class Model {
    private PublishSubject<ActionData> publisher = PublishSubject.create();

    protected final void emit(final ActionData event) {
        this.publisher.onNext(event);
    }

    protected final void emitException(Exception ex){
        this.publisher.onError(ex);
    }

    public PublishSubject<ActionData> getPublisher() {
        return publisher;
    }
}
