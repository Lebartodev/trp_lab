import model.CategoryItem;
import util.MarshallerUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class StartEditCategory implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NoSuchAlgorithmException {
        try {
            request.setAttribute("category", MarshallerUtil.marshallAction(ControllerSQL.getCategory(Integer.parseInt(request.getParameter("categoryId"))), CategoryItem.class));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "/resources/include/edit.jsp";
    }
}
