import model.CategoryItem;
import model.MovieItem;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlType(propOrder = {"categories", "movies", "filmId", "categoryId"}, name = "dataObject")
@XmlRootElement
public class DataObject implements Serializable{
    private static String filename = "model.dat";

    private Map<Integer, CategoryItem> categories = new HashMap<Integer, CategoryItem>();

    private Map<Integer, MovieItem> movies = new HashMap<Integer, MovieItem>();

    private int filmId;

    private int categoryId;

    private List<Integer> lockedMovies = new ArrayList<>();

    private List<Integer> lockedCategories = new ArrayList<>();

    public DataObject() {
    }

    static String getFilename() {
        return filename;
    }

    static void setFilename(String filename) {
        DataObject.filename = filename;
    }

    @XmlElementWrapper(name = "categories")
    @XmlElement(name = "categoryItem")
    public Map<Integer, CategoryItem> getCategories() {
        return categories;
    }

    public void setCategories(Map<Integer, CategoryItem> categories) {
        this.categories = categories;
    }

    @XmlElementWrapper(name = "movies")
    @XmlElement(name = "movieItem")
    public Map<Integer, MovieItem> getMovies() {
        return movies;
    }

    public void setMovies(Map<Integer, MovieItem> movies) {
        this.movies = movies;
    }

    public int getFilmId() {
        return ++filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getCategoryId() {
        return ++categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    List<Integer> getLockedMovies() {
        return lockedMovies;
    }

    void setLockedMovies(List<Integer> lockedMovies) {
        this.lockedMovies = lockedMovies;
    }

    List<Integer> getLockedCategories() {
        return lockedCategories;
    }

    void setLockedCategories(List<Integer> lockedCategories) {
        this.lockedCategories = lockedCategories;
    }

}
