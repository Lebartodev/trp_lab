import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nastya on 21.03.2016.
 */
public class RequestHelper {
    Map<String,Command> commands = new HashMap<String, Command>();
    public RequestHelper(){
        //commands.put("auth",new AuthCommand());
        //commands.put("logout",new LogoutCommand());
        /*commands.put("show",new ShowCommand());
        commands.put("add",new AddCommand());
        commands.put("addNew",new AddNewTaskCommand());
        commands.put("editEvent",new EditTaskCommand());
        commands.put("edit",new EditCommand());
        commands.put("deleteEvent",new DeleteTaskCommand());
        commands.put("tasksEmployees",new TaskEmpCommand());
        commands.put("register",new RegisterCommand());
        commands.put("registerNewUser",new RegisterUserCommand());
        commands.put("search",new SearchCommand());
        commands.put("save",new SaveCommand());*/
        commands.put("listEmployees",new CategoriesList());

    }
    public Command getCommand(HttpServletRequest request){
        String command =  request.getParameter("command");
        if(command == null){
            return null;
        }
        return commands.get(command);
    }
}
