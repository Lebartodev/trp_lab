import model.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Created by Nastya on 21.03.2016.
 */
public class EditTaskCommand implements Command {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NoSuchAlgorithmException {

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String date = request.getParameter("date");
        String contacts = request.getParameter("contacts");
        try {
            ControllerSQL c = ControllerSQL.getInstance();
            Task t = c.getEditTask();
            t.setName(name);
            t.setDateStr(date);
            t.setDescription(description);
            t.setContacts(contacts);


            c.updateTask(t);

            request.setAttribute("success", "success");
        }
        catch (Exception e){
            request.setAttribute("success", "error");
        }
        return "/TaskManager?command=show";
    }
}
