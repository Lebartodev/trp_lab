import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class MainServlet extends HttpServlet {
    RequestHelper requestHelper = new RequestHelper();

    @EJB
    private IControllerSQL controllerSQL;




    //    String[] message;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            doRequest(request, response, controllerSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            doRequest(request, response, controllerSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    protected void doRequest(HttpServletRequest request, HttpServletResponse response, IControllerSQL controllerSQL) throws ServletException, IOException, SQLException, NoSuchAlgorithmException, NamingException, ClassNotFoundException {
        response.setContentType("text/html");

        Command command = requestHelper.getCommand(request);
        String page  = command.execute(request,response, controllerSQL);
        request.getRequestDispatcher(page).forward(request, response);

    }
}
