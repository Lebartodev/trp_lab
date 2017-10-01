package model;

import base.Model;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import model.data.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainModel extends Model {

    String filename = "model.dat";

    List<CategoryItem> categories = new ArrayList<>();

    List<MovieItem> movies = new ArrayList<>();

    int lastFilmId;

    public MainModel() {
        File file = new File("model.dat");
        if (file.exists()) {
            deserializeModel();
        } else {
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

            categories.add(CategoryItem.newBuilder().id(0)
                    .name("Category 0").build());
            categories.add(CategoryItem.newBuilder().id(1)
                    .name("Category 1").build());
            categories.add(CategoryItem.newBuilder().id(2)
                    .name("Category 2").build());

            lastFilmId = 12;
        }

        calculateLastFilmId();
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

        int lastCategory = 0;

        for (CategoryItem category : categories) {
            if(category.getId()>lastCategory){
                lastCategory = category.getId();
            }
        }

        CategoryItem catNew = CategoryItem.newBuilder().id(lastCategory)
                .name(name).build();

        categories.add(catNew);
        serializeModel();

        emit(new ActionShowCategories(categories));
    }

    public void onCreateMovie(String name, int year, String description
            , int genreId, int budget) {
        MovieItem movieNew = MovieItem.newBuilder()
                .id(lastFilmId).name(name).year(year).description(description)
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
                lastFilmId++;
                serializeModel();
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
        for (MovieItem movie : movies) {
            if(movie.getGenreId()==id){
                movies.remove(movie);
            }
        }
        for (CategoryItem category : categories) {
            if(category.getId()==id){
                categories.remove(category);
                serializeModel();
                return;
            }
        }
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

    private void serializeModel() {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this.categories);
            oos.writeObject(this.movies);
            System.out.println("Запись произведена");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void deserializeModel() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            this.categories = (ArrayList<CategoryItem>) ois.readObject();
            this.movies = (ArrayList<MovieItem>) ois.readObject();
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    private void calculateLastFilmId() {
        lastFilmId = 0;
        for (MovieItem movieItem : movies) {
            if (movieItem.getId() > lastFilmId)
                lastFilmId = movieItem.getId();
        }
    }
}
