package main.java.model.data.request;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"categoryId"}, name = "requestShowMovieList")
public class RequestShowMovieList {
    public RequestShowMovieList(int categoryId) {
        this.categoryId = categoryId;
    }

    public RequestShowMovieList() {
    }

    private int categoryId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
