package model;

import java.util.List;

public class CategoryItem {
    private int id;
    private String name;
    private List<MovieItem> movies;

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

    public List<MovieItem> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieItem> movies) {
        this.movies = movies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryItem that = (CategoryItem) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return movies != null ? movies.equals(that.movies) : that.movies == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (movies != null ? movies.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CategoryItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", movies=" + movies +
                '}';
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

        public Builder movies(List<MovieItem> movies){
            CategoryItem.this.movies = movies;
            return this;
        }

        public CategoryItem build(){
            return CategoryItem.this;
        }
    }
}
