package model.data.response;

import model.CategoryItem;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlType(propOrder = {"categories"}, name = "responseShowCategories")
public class ResponseShowCategories {
    private List<CategoryItem> categories = new ArrayList<>();

    public ResponseShowCategories() {
    }

    public ResponseShowCategories(List<CategoryItem> categories) {
        this.categories = categories;
    }

    public void setCategories(List<CategoryItem> categories) {
        this.categories = categories;
    }

    @XmlElementWrapper(name = "categories")
    @XmlElement(name = "categoryItem")
    public List<CategoryItem> getCategories() {
        return categories;
    }
}
