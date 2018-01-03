import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class StartEditCategory implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NoSuchAlgorithmException {
        try {
            request.setAttribute("category", ControllerSQL.getCategory(Integer.parseInt(request.getParameter("categoryId"))));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "/resources/include/edit.jsp";
    }
}
