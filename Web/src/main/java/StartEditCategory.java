import util.CategoryItem;
import util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class StartEditCategory implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, IControllerSQL controllerSQL) throws SQLException, NoSuchAlgorithmException {
        try {
            request.setAttribute("category", Util.marshall(controllerSQL.getCategory(Integer.parseInt(request.getParameter("categoryId"))), CategoryItem.class));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "/resources/include/edit.jsp";
    }
}
