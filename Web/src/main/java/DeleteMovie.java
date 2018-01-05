import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class DeleteMovie implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, IControllerSQL controllerSQL) throws SQLException, NoSuchAlgorithmException {
        try {
            controllerSQL.deleteMovie(Integer.parseInt(request.getParameter("movieId")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "/MovieCatalog?command=listMoviesInCategory&categoryId=" + request.getParameter("movieGenreId");
    }
}
