package main.java.model.data.request;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"movieId"}, name = "requestStartMovieEdit")
public class RequestStartMovieEdit {
    private int movieId;

    public RequestStartMovieEdit() {
    }

    public RequestStartMovieEdit(int movieId) {
        this.movieId = movieId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
