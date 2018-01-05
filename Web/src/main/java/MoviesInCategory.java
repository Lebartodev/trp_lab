import util.MovList;
import util.MovieItem;
import util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class MoviesInCategory implements Command {
    public String execute(HttpServletRequest request, HttpServletResponse response, IControllerSQL controllerSQL) throws SQLException, NoSuchAlgorithmException {
        List<MovieItem> arrayList = null;
        try {
            arrayList = controllerSQL.getMoviesInCategory(Integer.parseInt(request.getParameter("categoryId")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("kek");
        request.setAttribute("moviesList", Util.marshall(new MovList(arrayList), MovList.class));


        return "/resources/include/tableMovies.jsp";
    }
}
