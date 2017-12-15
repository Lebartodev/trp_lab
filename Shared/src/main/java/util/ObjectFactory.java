package util;

import model.data.request.RequestShowCategories;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
    public RequestShowCategories createRequestShowCategories() {
        return new RequestShowCategories();
    }

    // and so on
}
