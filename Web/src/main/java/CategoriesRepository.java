import util.CatList;
import util.CategoryItem;
import util.Util;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.*;

@ManagedBean
@ApplicationScoped
public class CategoriesRepository implements Serializable {
    @EJB(lookup = "java:module/storage")
    private IControllerSQL controllerSQL;

    public List<CategoryItem> getCategoryItems() throws SQLException, ClassNotFoundException {
        return controllerSQL.getCategories();
    }

    public void createCategory(String name){
        try {
            controllerSQL.createCategory(CategoryItem.newBuilder().name(name).build());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}