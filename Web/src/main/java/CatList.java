import model.CategoryItem;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement
@XmlType(propOrder = {"categoryItemLIst"}, name = "catList")
public class CatList {
    private List<CategoryItem> categoryItemLIst;

    public CatList(List<CategoryItem> categoryItemLIst) {
        this.categoryItemLIst = categoryItemLIst;
    }

    public CatList() {
    }

    @XmlElementWrapper(name = "categoryItemLIst")
    @XmlElement(name = "categoryItem")
    public List<CategoryItem> getCategoryItemLIst() {
        return categoryItemLIst;
    }

    public void setCategoryItemLIst(List<CategoryItem> categoryItemLIst) {
        this.categoryItemLIst = categoryItemLIst;
    }
}
