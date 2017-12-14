package main.java.model.data.request;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement
@XmlType(propOrder = {"id"}, name = "requestDeleteCategory")
public class RequestDeleteCategory implements Serializable {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public RequestDeleteCategory() {
    }

    public RequestDeleteCategory(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
