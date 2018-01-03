import model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by 0_o on 22.04.2016.
 */
public class EmployeesList implements Command {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NoSuchAlgorithmException {
        ControllerSQL c = ControllerSQL.getInstance();
        List<User> arrayList = c.getEmploes();

        request.setAttribute("arrayList",arrayList);


        return "/resources/include/tableEmployees.jsp";
    }
}
