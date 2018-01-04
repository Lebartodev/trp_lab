import util.MarshallerUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Search implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NoSuchAlgorithmException {
        try {
            request.setAttribute("categoryList", MarshallerUtil.marshallAction(new CatList(ControllerSQL.searchCategory(request.getParameter("inputSearch"))), CatList.class));
            request.setAttribute("movieList", MarshallerUtil.marshallAction(new MovList(ControllerSQL.searchMovie(request.getParameter("inputSearch"))), MovList.class) );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "resources/include/tableResult.jsp";
    }
}
