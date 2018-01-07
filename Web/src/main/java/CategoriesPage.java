import util.CategoryItem;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ManagedBean
@RequestScoped
public class CategoriesPage {
    private UIInput input1;

    @EJB(lookup = "java:module/storage")
    private IControllerSQL controllerSQL;

    public Collection<CategoryItem> getCategories(){
        List<CategoryItem> categoryItems = new ArrayList<>();
        try {
            categoryItems = controllerSQL.getCategories();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return categoryItems;
    }

    public void createCategory(String name){
        try {
            controllerSQL.createCategory(CategoryItem.newBuilder().name(name).build());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("categoriesList.jsf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UIInput getInput1() {
        return input1;
    }

    public void setInput1(UIInput input1) {
        this.input1 = input1;
    }

    public void deleteCategory(Object id){
        try {
            controllerSQL.deleteCategory((Integer) id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("categoriesList.jsf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editCategory(int id, String name){
        try {
            controllerSQL.updateCategory(CategoryItem.newBuilder().id(id).name(name).build());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("categoriesList.jsf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}