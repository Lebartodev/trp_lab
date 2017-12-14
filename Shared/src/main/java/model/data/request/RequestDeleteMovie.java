package main.java.model.data.request;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
@XmlRootElement
@XmlType(propOrder = {"movieId"}, name = "requestDeleteMovie")
public class RequestDeleteMovie implements  Serializable{
    private int movieId;

    public RequestDeleteMovie(int movieId) {
        this.movieId = movieId;
    }

    public RequestDeleteMovie() {
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
