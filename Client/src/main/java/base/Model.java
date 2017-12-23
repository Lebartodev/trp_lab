package base;

import io.reactivex.Single;
import io.reactivex.subjects.PublishSubject;
import org.w3c.dom.Document;

public abstract class Model {


    public abstract PublishSubject<Object> getPublisher();

    public abstract Single<? super Object> send(Document command);
}
