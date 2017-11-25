package base;

import io.reactivex.subjects.PublishSubject;

public abstract class Model {
    private PublishSubject<base.ActionData> publisher = PublishSubject.create();

    protected final void emit(final base.ActionData event) {
        this.publisher.onNext(event);
    }

    protected final void emitException(Exception ex){
        this.publisher.onError(ex);
    }

    public PublishSubject<base.ActionData> getPublisher() {
        return publisher;
    }
}
