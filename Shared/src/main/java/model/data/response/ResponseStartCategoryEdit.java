package model.data.response;


import model.CategoryItem;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Created by pavel on 29.09.17.
 */

@XmlRootElement
@XmlType(propOrder = {"category"}, name = "responseStartCategoryEdit")
public class ResponseStartCategoryEdit {
    private CategoryItem category;

    public ResponseStartCategoryEdit() {
    }

    public ResponseStartCategoryEdit(CategoryItem category) {
        this.category = category;
    }

    public void setCategory(CategoryItem category) {
        this.category = category;
    }

    public CategoryItem getCategory() {
        return category;
    }
}
