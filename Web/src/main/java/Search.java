import util.CatList;
import util.MovList;
import util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Search implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, IControllerSQL controllerSQL) throws SQLException, NoSuchAlgorithmException {
        try {
            request.setAttribute("categoryList", Util.marshall(new CatList(controllerSQL.searchCategory(request.getParameter("inputSearch"))), CatList.class));
            request.setAttribute("movieList", Util.marshall(new MovList(controllerSQL.searchMovie(request.getParameter("inputSearch"))), MovList.class) );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "resources/include/tableResult.jsp";
    }
}
