import util.MovieItem;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class MovieRepository {
    private List movies = new ArrayList();

    @EJB(lookup = "java:module/storage")
    private IControllerSQL controllerSQL;

    public Collection<MovieItem> getMovies(int id) {
        try {
            movies = controllerSQL.getMoviesInCategory(id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public MovieItem getMovie(int id) throws SQLException, ClassNotFoundException {
        return controllerSQL.getMovie(id);
    }
}
