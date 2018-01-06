import util.CategoryItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class EndCreateCategory implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, IControllerSQL controllerSQL) throws SQLException, NoSuchAlgorithmException {
        CategoryItem categoryItem = CategoryItem.newBuilder().name(request.getParameter("categoryName")).build();
        try {
            controllerSQL.createCategory(categoryItem);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "/MovieCatalog?command=listCategories";
    }
}
