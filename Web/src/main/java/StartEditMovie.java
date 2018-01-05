import model.MovieItem;
import util.CatList;
import util.MarshallerUtil;
import util.MovList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class StartEditMovie implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, IControllerSQL controllerSQL) throws SQLException, NoSuchAlgorithmException {
        try {
            request.setAttribute("movie", MarshallerUtil.marshallAction(controllerSQL.getMovie(Integer.parseInt(request.getParameter("movieId"))), MovieItem.class));
            request.setAttribute("categoryList", MarshallerUtil.marshallAction(new CatList(controllerSQL.getCategories()), MovList.class));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "/resources/include/editMovie.jsp";
    }
}
