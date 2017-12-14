package main.java.base;

import io.reactivex.Single;
import io.reactivex.subjects.PublishSubject;
import org.w3c.dom.Document;

public abstract class Model {


    public abstract PublishSubject<Document> getPublisher();

    public abstract Single<? super Document> send(Document command);
}
