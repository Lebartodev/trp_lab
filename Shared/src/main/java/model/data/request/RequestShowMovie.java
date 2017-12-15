package model.data.request;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
@XmlRootElement
@XmlType(propOrder = {"movieId"}, name = "requestShowMovie")
public class RequestShowMovie {
    public RequestShowMovie(int movieId) {
        this.movieId = movieId;
    }

    public RequestShowMovie() {
    }

    private int movieId;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
