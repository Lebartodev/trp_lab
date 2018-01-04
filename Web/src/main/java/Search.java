import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Search implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NoSuchAlgorithmException {
        try {
            request.setAttribute("categoryList", ControllerSQL.searchCategory(request.getParameter("inputSearch")));
            request.setAttribute("movieList", ControllerSQL.searchMovie(request.getParameter("inputSearch")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "/MovieCatalog?command=search";
    }
}
