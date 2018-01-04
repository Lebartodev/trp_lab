import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nastya on 21.03.2016.
 */
public class RequestHelper {
    Map<String,Command> commands = new HashMap<String, Command>();
    public RequestHelper(){
        commands.put("endEditMovie", new EndEditMovie());
        commands.put("startEditMovie", new StartEditMovie());
        commands.put("deleteMovie", new DeleteMovie());
        commands.put("endCreateMovie", new EndCreateMovie());
        commands.put("startCreateMovie", new StartCreateMovie());
        commands.put("endCreateCategory",new EndCreateCategory());
        commands.put("startCreateCategory",new StartCreateCategory());
        commands.put("deleteCategory",new DeleteCategory());
        commands.put("endEditCategory",new EndEditCategory());
        commands.put("startEditCategory",new StartEditCategory());
        commands.put("listMoviesInCategory",new MoviesInCategory());
        commands.put("listCategories",new CategoriesList());

    }
    public Command getCommand(HttpServletRequest request){
        String command =  request.getParameter("command");
        if(command == null){
            return new CategoriesList();
        }
        return commands.get(command);
    }
}
