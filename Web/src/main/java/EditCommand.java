import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Created by Nastya on 21.03.2016.
 */
public class EditCommand implements Command {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NoSuchAlgorithmException {

        int id = Integer.parseInt(request.getParameter("task"));
        ControllerSQL c = ControllerSQL.getInstance();
        c.setEditTask(c.getTaskById(id));
        request.setAttribute("task",c.getTaskById(id));



        return "/resources/include/edit.jsp";
    }
}
