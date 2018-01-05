import util.CatList;
import util.CategoryItem;
import util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by 0_o on 22.04.2016.
 */
public class CategoriesList implements Command {
    public String execute(HttpServletRequest request, HttpServletResponse response, IControllerSQL controllerSQL) throws SQLException, NoSuchAlgorithmException {
        List<CategoryItem> arrayList = null;
        try {
            arrayList = controllerSQL.getCategories();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("kek");
        request.setAttribute("categoriesList", Util.marshall(new CatList(arrayList), CatList.class));


        return "/resources/include/table.jsp";
    }
}
