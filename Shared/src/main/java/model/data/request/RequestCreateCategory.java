package main.java.model.data.request;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement
@XmlType(propOrder = {"categoryName"}, name = "requestCreateCategory")
public class RequestCreateCategory implements Serializable {
    private String categoryName;

    public RequestCreateCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public RequestCreateCategory() {

    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
