package main.java.base;

import io.reactivex.Single;
import io.reactivex.subjects.PublishSubject;
import main.java.ActionData;

public abstract class Model {


    public abstract PublishSubject<ActionData> getPublisher();

    public abstract Single<? super ActionData> send(ActionData command);
}
