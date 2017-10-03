package model;

import base.Model;
import model.data.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MainModel extends Model {

    private String filename = "model.dat";

    private List<CategoryItem> categories = new ArrayList<>();

    private List<MovieItem> movies = new ArrayList<>();

    private AtomicInteger filmId = new AtomicInteger();

    private AtomicInteger categoryId = new AtomicInteger();

    public MainModel() {
        File file = new File("model.dat");
        if (file.exists()) {
            deserializeModel();
        } else {
            testData();
        }
    }

    public void getCategories() {

        emit(new ActionShowCategories(categories));
    }

    public void getMoviesInCategory(int id) {

        List<MovieItem> moviesInCategory = new ArrayList<>();
        for (MovieItem movie : movies) {
            if (movie.getGenreId() == id) {
                moviesInCategory.add(movie);
            }
        }

        for (CategoryItem category : categories) {
            if(category.getId()==id){
                emit(new ActionShowMoviesInCategory(category
                        .getName(), moviesInCategory));
                return;
            }
        }

    }

    public void getMovie(int id) {
        for (MovieItem movie : movies) {
            if (movie.getId() == id) {
                emit(new ActionShowMovie(movie));
                return;
            }
        }

    }

    public void createCategory(String name) {

        CategoryItem catNew = CategoryItem.newBuilder().id(createCategoryId())
                .name(name).build();

        categories.add(catNew);
        serializeModel();

        emit(new ActionShowCategories(categories));
    }

    public void onCreateMovie(String name, int year, String description
            , int genreId, int budget) {
        MovieItem movieNew = MovieItem.newBuilder()
                .id(createFilmId()).name(name).year(year).description(description)
                .genreId(genreId).budget(budget).build();
        movies.add(movieNew);
        serializeModel();
        List<MovieItem> moviesInCategory = new ArrayList<>();
        for (MovieItem movie : movies) {
            if (movie.getGenreId() == genreId)
                moviesInCategory.add(movie);
        }
        for (CategoryItem category : categories) {
            if(category.getId()==genreId){
                emit(new ActionOnCreateMovie(categories, category
                        , moviesInCategory, movieNew));
                return;
            }
        }

    }

    public void onEditCategory(int id){
        for (CategoryItem category : categories) {
            if(category.getId()==id){
                emit(new ActionOnEditCategory(category.getName()));
                return;
            }
        }
    }

    public void editCategory(int id, String name){
        for (CategoryItem category : categories) {
            if(category.getId()==id){
                category.setName(name);
                serializeModel();
                return;
            }
        }
    }

    public void deleteCategory(int id){
        List<MovieItem> moviesForDelete = new ArrayList<>();
        for (MovieItem movie : movies) {
            if(movie.getGenreId()==id){
                moviesForDelete.add(movie);
            }
        }
        movies.removeAll(moviesForDelete);
        CategoryItem categoryForDelete = new CategoryItem();
        for (CategoryItem category : categories) {
            if(category.getId()==id){
                categoryForDelete = category;
            }
        }
        categories.remove(categoryForDelete);
        serializeModel();
    }

    public void editMovie(int id, String name, int year,
                          String description, int genreId, int budget){
        MovieItem editMovie = new MovieItem();
        for (MovieItem movie : movies) {
            if(movie.getId()==id){
                editMovie = movie;
                break;
            }
        }
        editMovie.setName(name);
        editMovie.setYear(year);
        editMovie.setDescription(description);
        editMovie.setGenreId(genreId);
        editMovie.setBudget(budget);
        serializeModel();
    }

    public void deleteMovie(int id){
        movies.remove(movies.get(id));
        serializeModel();
    }

    private void testData(){
        for (int i = 0; i < 4; i++) {
            movies.add(MovieItem.newBuilder().id(i).name("Test film " + i)
                    .year(1990 + i).description("Test description for Test film " + i)
                    .genreId(0).budget(500 + i).build());
        }

        for (int i = 4; i < 8; i++) {
            movies.add(MovieItem.newBuilder().id(i).name("Test film " + i)
                    .year(1990 + i).description("Test description for Test film " + i)
                    .genreId(1).budget(500 + i).build());
        }

        for (int i = 8; i < 12; i++) {
            movies.add(MovieItem.newBuilder().id(i).name("Test film " + i)
                    .year(1990 + i).description("Test description for Test film " + i)
                    .genreId(2).budget(500 + i).build());
        }
        filmId.set(11);

        categories.add(CategoryItem.newBuilder().id(0)
                .name("Category 0").build());
        categories.add(CategoryItem.newBuilder().id(1)
                .name("Category 1").build());
        categories.add(CategoryItem.newBuilder().id(2)
                .name("Category 2").build());
        categoryId.set(2);

    }

    private void serializeModel() {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this.categories);
            oos.writeObject(this.movies);
            oos.writeObject(this.filmId);
            oos.writeObject(this.categoryId);
            System.out.println("Запись произведена");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void deserializeModel() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            this.categories = (ArrayList<CategoryItem>) ois.readObject();
            this.movies = (ArrayList<MovieItem>) ois.readObject();
            this.filmId = (AtomicInteger) ois.readObject();
            this.categoryId = (AtomicInteger) ois.readObject();
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    private int createFilmId() {
        return filmId.incrementAndGet();
    }

    private int createCategoryId(){
        return categoryId.incrementAndGet();
    }
}
