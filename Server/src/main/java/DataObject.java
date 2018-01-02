import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataObject implements Serializable{

    private int filmId;

    private int categoryId;

    private List<Integer> lockedMovies = new ArrayList<>();

    private List<Integer> lockedCategories = new ArrayList<>();

    public DataObject() {
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
