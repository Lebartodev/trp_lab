package base;

import io.reactivex.internal.operators.flowable.FlowablePublish;
import io.reactivex.subjects.PublishSubject;
import model.data.ActionEmpty;

public abstract class Model {
    private PublishSubject<ActionData> publisher = PublishSubject.create();

    public PublishSubject<ActionData> getPublisher() {
        return publisher;
    }
}
