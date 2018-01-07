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
    private List<CategoryItem> categoryItems = new ArrayList<>();
    @EJB(lookup = "java:module/storage")
    private IControllerSQL controllerSQL;

    @PostConstruct
    protected void init() {
        try {
            categoryItems = controllerSQL.getCategories();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CategoryItem> getCategoryItems() {
        return categoryItems;
    }

    public void setCategoryItems(List<CategoryItem> categoryItems) {
        this.categoryItems = categoryItems;
    }

    public void createCategory(String name){
        try {
            controllerSQL.createCategory(CategoryItem.newBuilder().name(name).build());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}