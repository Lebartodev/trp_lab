import util.MovieItem;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.Collection;

@ManagedBean
@RequestScoped
public class MoviePage {
    @ManagedProperty("#{movieRepository}")
    private MovieRepository movieRepository;

    private int selectedId;
    private MovieItem selectedMovie;

    public Collection<MovieItem> getMovies() {
        return movieRepository.getMovies();
    }

    public void loadMovie() {
        selectedMovie = movieRepository.getMovie(selectedId);
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
