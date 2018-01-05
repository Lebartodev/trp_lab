package util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement
@XmlType(propOrder = {"categoryItemList"}, name = "catList")
public class CatList {
    private List<CategoryItem> categoryItemList;

    public CatList(List<CategoryItem> categoryItemList) {
        this.categoryItemList = categoryItemList;
    }

    public CatList() {
    }

    @XmlElementWrapper(name = "categoryItemList")
    @XmlElement(name = "categoryItem")
    public List<CategoryItem> getCategoryItemList() {
        return categoryItemList;
    }

    public void setCategoryItemList(List<CategoryItem> categoryItemList) {
        this.categoryItemList = categoryItemList;
    }
}
