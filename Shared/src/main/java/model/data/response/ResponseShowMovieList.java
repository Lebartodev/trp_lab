package model.data.response;

import model.CategoryItem;
import model.MovieItem;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@XmlRootElement
@XmlType(propOrder = {"categoryItem", "movies", "oldCategoryItem"}, name = "responseShowMovieList")
public class ResponseShowMovieList  {
    private CategoryItem categoryItem;

    private List<MovieItem> movies = new LinkedList<>();

    private CategoryItem oldCategoryItem;

    public ResponseShowMovieList() {
    }

    public ResponseShowMovieList(List<MovieItem> movies, CategoryItem categoryItem) {
        this.categoryItem = categoryItem;
        this.movies = movies;
    }

    public ResponseShowMovieList(CategoryItem categoryItem, List<MovieItem> movies, CategoryItem oldCategoryItem) {
        this.categoryItem = categoryItem;
        this.movies = movies;
        this.oldCategoryItem = oldCategoryItem;
    }

    @XmlElement(name = "movieItem")
    @XmlElementWrapper(name = "movies")
    public List<MovieItem> getMovies() {
        return movies;
    }

    public CategoryItem getCategoryItem() {
        return categoryItem;
    }

    public void setCategoryItem(CategoryItem categoryItem) {
        this.categoryItem = categoryItem;
    }

    public void setMovies(List<MovieItem> movies) {
        this.movies = movies;
    }

    public CategoryItem getOldCategoryItem() {
        return oldCategoryItem;
    }

    public void setOldCategoryItem(CategoryItem oldCategoryItem) {
        this.oldCategoryItem = oldCategoryItem;
    }
}
