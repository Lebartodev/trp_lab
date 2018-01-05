import org.w3c.dom.Document;
import util.CatList;
import util.MovList;
import util.MovieItem;
import util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class StartEditMovie implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, IControllerSQL controllerSQL) throws SQLException, NoSuchAlgorithmException {
        try {
            request.setAttribute("movie", Util.marshall(controllerSQL.getMovie(Integer.parseInt(request.getParameter("movieId"))), MovieItem.class));

            request.setAttribute("categoryList", Util.marshall(new CatList(controllerSQL.getCategories()), CatList.class));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "/resources/include/editMovie.jsp";
    }
}
