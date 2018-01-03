import model.MovieItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class MoviesInCategory implements Command {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NoSuchAlgorithmException {
        List<MovieItem> arrayList = null;
        try {
            arrayList = ControllerSQL.getMoviesInCategory(Integer.parseInt(request.getParameter("categoryId")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("kek");
        request.setAttribute("moviesList",arrayList);


        return "/resources/include/table.jsp";
    }
}
