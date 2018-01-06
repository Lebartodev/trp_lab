import util.CategoryItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class EndEditCategory implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, IControllerSQL controllerSQL) throws SQLException, NoSuchAlgorithmException {
        try {
            CategoryItem categoryItem = CategoryItem.newBuilder().id(Integer.parseInt(request.getParameter("categoryId")))
                    .name(request.getParameter("categoryName")).build();
            controllerSQL.updateCategory(categoryItem);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "/MovieCatalog?command=listCategories";
    }
}
