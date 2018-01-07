import util.MovieItem;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

@ManagedBean
@ApplicationScoped
public class MovieRepository {
    private Map<Integer, MovieItem> movies = new TreeMap<Integer, MovieItem>();

    @PostConstruct
    protected void init() {
        try {
            ControllerSQLBean controllerSQLBean = new ControllerSQLBean();
            for (MovieItem movieItem : controllerSQLBean.getMoviesInCategory(2)) {
                movies.put(movieItem.getId(), movieItem);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Collection<MovieItem> getMovies() {
        return movies.values();
    }

    public MovieItem getMovie(int id) {
        return movies.get(id);
    }
}
