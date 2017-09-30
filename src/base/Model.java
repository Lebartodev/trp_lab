package base;

import io.reactivex.internal.operators.flowable.FlowablePublish;
import io.reactivex.subjects.PublishSubject;
import model.data.ActionEmpty;

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
