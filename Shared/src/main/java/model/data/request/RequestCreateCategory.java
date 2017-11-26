package main.java.model.data.request;

public class RequestCreateCategory {
    private String categoryName;

    public RequestCreateCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
