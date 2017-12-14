package main.java.model.data.request;


public class RequestShowMovieList {
    public RequestShowMovieList(int categoryId) {
        this.categoryId = categoryId;
    }

    private int categoryId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
