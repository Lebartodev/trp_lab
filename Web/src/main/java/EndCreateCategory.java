import model.CategoryItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class EndCreateCategory implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NoSuchAlgorithmException {
        CategoryItem categoryItem = CategoryItem.newBuilder().id(Integer.parseInt(request.getParameter("categoryId")))
                .name(request.getParameter("categoryName")).build();
        try {
            ControllerSQL.createCategory(categoryItem);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "/MovieCatalog";
    }
}
