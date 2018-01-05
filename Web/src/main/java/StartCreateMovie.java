import util.CatList;
import util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class StartCreateMovie implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, IControllerSQL controllerSQL) throws SQLException, NoSuchAlgorithmException {
        try {
            request.setAttribute("categoryList", Util.marshall(new CatList(controllerSQL.getCategories()), CatList.class));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "/resources/include/addMovie.jsp";
    }
}
