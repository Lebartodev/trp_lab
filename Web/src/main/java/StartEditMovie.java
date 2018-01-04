import model.MovieItem;
import util.MarshallerUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class StartEditMovie implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NoSuchAlgorithmException {
        try {
            request.setAttribute("movie", MarshallerUtil.marshallAction(ControllerSQL.getMovie(Integer.parseInt(request.getParameter("movieId"))), MovieItem.class));
            request.setAttribute("categoryList", MarshallerUtil.marshallAction(new CatList(ControllerSQL.getCategories()), MovList.class));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "/resources/include/editMovie.jsp";
    }
}
