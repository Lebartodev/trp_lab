import util.MovieItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class EndEditMovie implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, IControllerSQL controllerSQL) throws SQLException, NoSuchAlgorithmException {
        MovieItem movieItem = MovieItem.newBuilder()
                .id(Integer.parseInt(request.getParameter("movieId")))
                .name(request.getParameter("movieName"))
                .year(Integer.parseInt(request.getParameter("movieYear")))
                .description(request.getParameter("movieDescription"))
                .genreId(Integer.parseInt(request.getParameter("movieGenreId")))
                .budget(Integer.parseInt(request.getParameter("movieBudget"))).build();
        try {
            controllerSQL.updateMovie(movieItem);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "/MovieCatalog?command=listMoviesInCategory&categoryId=" + request.getParameter("movieGenreId");
    }
}
