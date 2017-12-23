package model.data.response;

import model.MovieItem;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement
@XmlType(propOrder = {"movie"}, name = "responseShowMovie")
public class ResponseShowMovie {
    private MovieItem movie;

    public ResponseShowMovie() {
    }

    public ResponseShowMovie(MovieItem movie) {
        this.movie = movie;
    }

    public void setMovie(MovieItem movie) {
        this.movie = movie;
    }

    public MovieItem getMovie() {
        return movie;
    }
}
