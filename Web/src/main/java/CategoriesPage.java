import util.CategoryItem;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@ManagedBean
@RequestScoped
public class CategoriesPage {
    @ManagedProperty("#{categoriesRepository}")
    private CategoriesRepository categoriesRepository;
    private UIInput input1;

    public CategoriesRepository getCategoriesRepository() {
        return categoriesRepository;
    }

    public void setCategoriesRepository(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public void createCategory(Object name){
        categoriesRepository.createCategory((String) name);
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
}