package main.java;

import main.java.model.CategoryItem;
import main.java.model.MovieItem;

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
        /*File file = new File("model.dat");
        if (file.exists()) {
            deserializeModel();
        } else {
            testData();
        }*/
    }

    public void getCategories() {

//        emit(new UpdateCategories(categories));
    }

    public void getMoviesInCategory(int id) {

        /*List<MovieItem> moviesInCategory = new ArrayList<>();
        for (CategoryItem category : categories) {
            if(category.getId()==id){
                emit(new UpdateMovies(category
                        .getName(), category.getMovies()));
                return;
            }
        }*/

    }

    public void getMovie(int id) {
        /*for (CategoryItem category : categories) {
            for (MovieItem movieItem : category.getMovies()) {
                if(movieItem.getId()==id){
                    emit(new ActionShowMovie(movieItem));
                    return;
                }
            }
        }*/

    }

    public void createCategory(String name) {

        /*CategoryItem catNew = CategoryItem.newBuilder().id(createCategoryId())
                .name(name).movies(new ConcurrentLinkedQueue()).build();

        categories.add(catNew);
        serializeModel();

//        emit(new UpdateCategories(categories));*/
    }

    public void onCreateMovie(String name, int year, String description
            , int genreId, int budget) {
//        MovieItem movieNew = MovieItem.newBuilder()
//                .id(createFilmId()).name(name).year(year).description(description)
//                .genreId(genreId).budget(budget).build();
//        for (CategoryItem category : categories) {
//            if(category.getId()==genreId){
//                category.getMovies().add(movieNew);
//                emit(new ResponseCreateMovie(categories, category
//                        , category.getMovies(), movieNew));
//                serializeModel();
//                return;
//            }
//        }
    }

    public void onEditCategory(int id){
        /*for (CategoryItem category : categories) {
            if(category.getId()==id){
                emit(new ResponseStartCategoryEdit(category.getName()));
                return;
            }
        }*/
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
        /*for (CategoryItem category : categories) {
            for (MovieItem movieItem : category.getMovies()) {
                if(movieItem.getId()==id){
                    movieItem.setName(name);
                    movieItem.setYear(year);
                    movieItem.setDescription(description);
                    movieItem.setGenreId(genreId);
                    movieItem.setBudget(budget);
                }
            }
        }

        serializeModel();*/
    }

    public void deleteMovie(int id){
        /*for (CategoryItem category : categories) {
            for (MovieItem movieItem : category.getMovies()) {
                if(movieItem.getId()==id){
                    category.getMovies().remove(movieItem);
                    return;
                }
            }
        }
        serializeModel();*/
    }

    /*private void testData(){
        ConcurrentLinkedQueue<MovieItem> movies1 = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < 4; i++) {
            movies1.add(MovieItem.newBuilder().id(i).name("Test film " + i)
                    .year(1990 + i).description("Test description for Test film " + i)
                    .genreId(0).budget(500 + i).build());
        }
        categories.add(CategoryItem.newBuilder().id(0)
                .name("Category 0").movies(movies1).build());

        ConcurrentLinkedQueue<MovieItem> movies2 = new ConcurrentLinkedQueue<>();

        for (int i = 4; i < 8; i++) {
            movies2.add(MovieItem.newBuilder().id(i).name("Test film " + i)
                    .year(1990 + i).description("Test description for Test film " + i)
                    .genreId(1).budget(500 + i).build());
        }

        categories.add(CategoryItem.newBuilder().id(1)
                .name("Category 1").movies(movies2).build());

        ConcurrentLinkedQueue<MovieItem> movies3 = new ConcurrentLinkedQueue<>();

        for (int i = 8; i < 12; i++) {
            movies3.add(MovieItem.newBuilder().id(i).name("Test film " + i)
                    .year(1990 + i).description("Test description for Test film " + i)
                    .genreId(2).budget(500 + i).build());
        }

        categories.add(CategoryItem.newBuilder().id(2)
                .name("Category 2").movies(movies3).build());


        filmId.set(11);
        categoryId.set(2);

    }*/

    private void serializeModel() {
    }

    private void deserializeModel() {
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
//            this.categories = (ArrayList<CategoryItem>) ois.readObject();
//            this.filmId = (AtomicInteger) ois.readObject();
//            this.categoryId = (AtomicInteger) ois.readObject();
//        } catch (Exception ex) {
//
//            ex.printStackTrace();
//        }
    }

    private int createFilmId() {
        return filmId.incrementAndGet();
    }

    private int createCategoryId(){
        return categoryId.incrementAndGet();
    }
}
