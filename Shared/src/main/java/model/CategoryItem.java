package main.java.model;

import java.util.concurrent.ConcurrentLinkedQueue;

public class CategoryItem implements java.io.Serializable{
    private int id;
    private String name;

    ConcurrentLinkedQueue<MovieItem> movies = new ConcurrentLinkedQueue<>();

    public CategoryItem() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ConcurrentLinkedQueue<MovieItem> getMovies() {
        return movies;
    }

    public void setMovies(ConcurrentLinkedQueue<MovieItem> movies) {
        this.movies = movies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryItem that = (CategoryItem) o;

        if (id != that.id) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CategoryItem{" + System.lineSeparator() +
                "id=" + id + System.lineSeparator() +
                ", name='" + name + '\'' + System.lineSeparator() +
                '}';
    }

    public static Builder newBuilder() {
        return new CategoryItem().new Builder();
    }

    public class Builder{
        private Builder(){

        }

        public Builder id(int id){
            CategoryItem.this.id = id;
            return this;
        }

        public Builder name(String name){
            CategoryItem.this.name = name;
            return this;
        }

        public Builder movies(ConcurrentLinkedQueue movies){
            CategoryItem.this.movies = movies;
            return this;
        }


        public CategoryItem build(){
            return CategoryItem.this;
        }
    }
}
