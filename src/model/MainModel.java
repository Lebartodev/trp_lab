package model;

import base.ActionData;
import base.Model;
import model.data.ActionOnCreateMovie;
import model.data.ActionShowCategories;
import model.data.ActionShowMovie;
import model.data.ActionShowMoviesInCategory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainModel extends Model {

    String filename = "model.dat";

    List<CategoryItem> categories = new ArrayList<>();

    public MainModel(){
        deserializeModel();
        if(categories.size()==0){
            List<MovieItem> movies = new ArrayList<>(4);
            for (int i = 1;i < 5; i++){
                movies.add(MovieItem.newBuilder().id(i).name("Test film " + i)
                        .year(1990 + i).description("Test description for Test film " + i)
                        .genreId(1).budget(500 + i).build());
            }

            CategoryItem cat1 = CategoryItem.newBuilder().id(1).name("Category 1").movies(movies).build();
            movies.clear();

            for (int i = 1;i < 5; i++){
                movies.add(MovieItem.newBuilder().id(i).name("Test film " + i)
                        .year(1990 + i).description("Test description for Test film " + i)
                        .genreId(2).budget(500 + i).build());
            }
            CategoryItem cat2 = CategoryItem.newBuilder().id(2).name("Category 2").movies(movies).build();
            movies.clear();

            for (int i = 1;i < 5; i++){
                movies.add(MovieItem.newBuilder().id(i).name("Test film " + i)
                        .year(1990 + i).description("Test description for Test film " + i)
                        .genreId(3).budget(500 + i).build());
            }
            CategoryItem cat3 = CategoryItem.newBuilder().id(3).name("Category 3").movies(movies).build();

            List<CategoryItem> listCat = new ArrayList<CategoryItem>(3);
            categories.add(cat1);
            categories.add(cat2);
            categories.add(cat3);
        }
    }

    public void getCategories(){

        emit(new ActionShowCategories(categories));

    }

    public void getMoviesInCategory(int id){

        emit(new ActionShowMoviesInCategory(categories.get(id-1)));
    }

    public void getMovie(int id){
        for (CategoryItem category : categories) {
            for (MovieItem movieItem : category.getMovies()) {
                if(movieItem.getId() == id)
                    emit(new ActionShowMovie(movieItem));
            }
        }

    }

    public void createCategory(){

        CategoryItem catNew = CategoryItem.newBuilder().id(categories.size()).name("Category " + categories.size()).build();

        categories.add(catNew);
        serializeModel();

        emit(new ActionShowCategories(categories));
    }

    public void onCreateMovie(){

        emit(new ActionOnCreateMovie(categories));

    }

    private void serializeModel(){

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename)))
        {
            oos.writeObject(this.categories);
            System.out.println("Запись произведена");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void deserializeModel(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename)))
        {
            this.categories=(ArrayList<CategoryItem>)ois.readObject();
        }
        catch(Exception ex){

            ex.printStackTrace();
        }
    }
}
