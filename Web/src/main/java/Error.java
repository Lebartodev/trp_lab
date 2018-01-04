import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Error implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NoSuchAlgorithmException {
        return "resources/include/error.jsp";
    }
}
