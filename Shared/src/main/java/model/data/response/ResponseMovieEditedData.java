package model.data.response;

import model.CategoryItem;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlType(propOrder = {"categories"}, name = "responseMovieEditedData")
public class ResponseMovieEditedData  {
    private List<CategoryItem> categories = new ArrayList<>();

    public ResponseMovieEditedData() {
    }

    public ResponseMovieEditedData(List<CategoryItem> categories) {
        this.categories = categories;
    }

    public void setCategories(List<CategoryItem> categories) {
        this.categories = categories;
    }

    @XmlElement(name = "categoryItem")
    @XmlElementWrapper(name = "categories")
    public List<CategoryItem> getCategories() {
        return categories;
    }
}