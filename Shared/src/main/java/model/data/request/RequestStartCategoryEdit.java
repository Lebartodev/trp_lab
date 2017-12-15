package model.data.request;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"categoryId"}, name = "requestStartCategoryEdit")
public class RequestStartCategoryEdit {

    private int categoryId;

    public RequestStartCategoryEdit() {
    }

    public RequestStartCategoryEdit(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
