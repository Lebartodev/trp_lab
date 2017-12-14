package main.java.model.data.request;


public class RequestStartCategoryEdit {

    private int categoryId;

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
