import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class EndEditMovie implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NoSuchAlgorithmException {
        try {
            ControllerSQL.updateMovie(Integer.parseInt(request.getParameter("movieId")),
                    request.getParameter("movieName"),
                    Integer.parseInt(request.getParameter("movieYear")),
                    request.getParameter("movieDescription"),
                    Integer.parseInt(request.getParameter("movieGenreId")),
                    Integer.parseInt(request.getParameter("movieBudget")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "/MovieCatalog?command=listMoviesInCategory&categoryId=" + request.getParameter("movieGenreId");
    }
}