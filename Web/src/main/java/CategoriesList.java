import model.CategoryItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by 0_o on 22.04.2016.
 */
public class CategoriesList implements Command {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NoSuchAlgorithmException {
        /*ControllerSQL c = ControllerSQL.getInstance();*/
        List<CategoryItem> arrayList = null;
        try {
            arrayList = ControllerSQL.getCategories();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("kek");
        request.setAttribute("categoriesList",arrayList);


        return "/resources/include/table.jsp";
    }
}