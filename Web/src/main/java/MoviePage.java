import util.MovieItem;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.sql.SQLException;
import java.util.Collection;

@ManagedBean
@RequestScoped
public class MoviePage {
    @ManagedProperty("#{movieRepository}")
    private MovieRepository movieRepository;

    private int selectedId;
    private MovieItem selectedMovie;

    public Collection<MovieItem> getMovies(int id) {
        return movieRepository.getMovies(id);
    }

    public void loadMovie() {
        try {
            selectedMovie = movieRepository.getMovie(selectedId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MovieItem getSelectedMovie() {
        return selectedMovie;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }

    public int getSelectedId() {
        return selectedId;
    }

    public void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
}
