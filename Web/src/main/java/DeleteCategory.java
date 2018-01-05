import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class DeleteCategory implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, IControllerSQL controllerSQL) throws SQLException, NoSuchAlgorithmException {
        try {
            controllerSQL.deleteCategory(Integer.parseInt(request.getParameter("categoryId")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "/MovieCatalog?command=listCategories";
    }
}
