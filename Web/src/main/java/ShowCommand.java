import model.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nastya on 21.03.2016.
 */
public class ShowCommand implements Command {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NoSuchAlgorithmException {
        ControllerSQL c = ControllerSQL.getInstance();
        List<Task> arrayList = c.getUserTasks();
        request.setAttribute("arrayList",arrayList);
        request.setAttribute("mode_emp",0);
        request.setAttribute("user_id",c.getCurrentUser().getId());

        return "/resources/include/table.jsp";
    }
}
