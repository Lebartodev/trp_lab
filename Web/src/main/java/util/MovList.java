package util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement
@XmlType(propOrder = {"movieItemList"}, name = "movList")
public class MovList {
    private List<MovieItem> movieItemList;

    public MovList(List<MovieItem> movieItemList) {
        this.movieItemList = movieItemList;
    }

    public MovList() {
    }

    @XmlElementWrapper(name = "movieItemList")
    @XmlElement(name = "categoryItem")
    public List<MovieItem> getMovieItemList() {
        return movieItemList;
    }

    public void setMovieItemList(List<MovieItem> movieItemList) {
        this.movieItemList = movieItemList;
    }
}
