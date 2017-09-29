package model;

import base.Model;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import model.data.ActionOnCreateMovie;
import model.data.ActionShowCategories;
import model.data.ActionShowMovie;
import model.data.ActionShowMoviesInCategory;

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
        if(file.exists()) {
            deserializeModel();
        }
            for (int i = 1; i < 5; i++) {
                movies.add(MovieItem.newBuilder().id(i).name("Test film " + i)
                        .year(1990 + i).description("Test description for Test film " + i)
                        .genreId(1).budget(500 + i).build());
            }

            for (int i = 5; i < 9; i++) {
                movies.add(MovieItem.newBuilder().id(i).name("Test film " + i)
                        .year(1990 + i).description("Test description for Test film " + i)
                        .genreId(2).budget(500 + i).build());
            }

            for (int i = 9; i < 13; i++) {
                movies.add(MovieItem.newBuilder().id(i).name("Test film " + i)
                        .year(1990 + i).description("Test description for Test film " + i)
                        .genreId(3).budget(500 + i).build());
            }

            lastFilmId = 13;

        calculateLastFilmId();
    }

    public void getCategories() {

        /*Single.just(cat)
                .toObservable()
                .flatMapIterable(categoryItems -> categoryItems)
                .map(categoryItem -> {
                    categoryItem.setMovies(new ArrayList<>());
                    return categoryItem;
                }).toList().doOnSuccess(categoryItems -> {
            List<CategoryItem> catdddd = new ArrayList<>(categories);
            emit(new ActionShowCategories(categoryItems));

        }).subscribe();*/
        emit(new ActionShowCategories(categories));
    }

    public void getMoviesInCategory(int id) {

        List<MovieItem> moviesInCategory = new ArrayList<>();
        for (MovieItem movie : movies) {
            if(movie.getGenreId()==id)
                moviesInCategory.add(movie);
        }

        emit(new ActionShowMoviesInCategory(categories.get(id-1).getName(), movies));
    }

    public void getMovie(int id) {
        for (MovieItem movie : movies) {
            if(movie.getId()==id){
                emit(new ActionShowMovie(movie));
            }
        }

    }

    public void createCategory(String name) {

        CategoryItem catNew = CategoryItem.newBuilder().id(categories.size() + 1)
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
            if(movie.getGenreId()==genreId)
                moviesInCategory.add(movie);
        }
        emit(new ActionOnCreateMovie(categories, categories.get(genreId - 1)
                , moviesInCategory, movieNew));

        lastFilmId++;

    }

    private void serializeModel() {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this.categories);
            System.out.println("Запись произведена");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void deserializeModel() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            this.categories = (ArrayList<CategoryItem>) ois.readObject();
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    private void calculateLastFilmId() {
        lastFilmId = 1;
            for (MovieItem movieItem : movies) {
                if (movieItem.getId() > lastFilmId)
                    lastFilmId = movieItem.getId();
            }
    }
}
