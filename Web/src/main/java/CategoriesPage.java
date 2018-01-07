import util.CategoryItem;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.Collection;
import java.util.List;

@ManagedBean
@RequestScoped
public class CategoriesPage {
    @ManagedProperty("#{categoriesRepository}")
    private CategoriesRepository categoriesRepository;

    public CategoriesRepository getCategoriesRepository() {
        return categoriesRepository;
    }

    public void setCategoriesRepository(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public void createCategory(String name){
        categoriesRepository.createCategory(name);
    }
}