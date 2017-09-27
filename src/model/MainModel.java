package model;

import base.ActionData;
import base.Model;
import model.data.ActionOnCreateMovie;
import model.data.ActionShowCategories;
import model.data.ActionShowMovie;
import model.data.ActionShowMoviesInCategory;

import java.util.ArrayList;
import java.util.List;

public class MainModel extends Model {

    public void getCategories(){
        
        CategoryItem cat1 = CategoryItem.newBuilder().id(1).name("Category 1").build();
        CategoryItem cat2 = CategoryItem.newBuilder().id(2).name("Category 2").build();
        CategoryItem cat3 = CategoryItem.newBuilder().id(3).name("Category 3").build();

        List<CategoryItem> listCat = new ArrayList<CategoryItem>(3);
        listCat.add(cat1);
        listCat.add(cat2);
        listCat.add(cat3);

        emit(new ActionShowCategories(listCat));

    }

    public void getMoviesInCategory(int id){
        List<MovieItem> movies = new ArrayList<>(4);
        for (int i = 1;i < 5; i++){
            movies.add(MovieItem.newBuilder().id(i).name("Test film " + i).build());
        }

        emit(new ActionShowMoviesInCategory(CategoryItem.newBuilder().id(id).name("Test category " + id)
                .movies(movies).build()));
    }

    public void getMovie(int id){
        emit(new ActionShowMovie(MovieItem.newBuilder().id(id).name("Test film " + id)
                .year(1990 + id).description("Test description for Test film " + id)
                .genreId(id).budget(500 + id).build()));
    }

    public void createCategory(){
        CategoryItem cat1 = CategoryItem.newBuilder().id(1).name("Category 1").build();
        CategoryItem cat2 = CategoryItem.newBuilder().id(2).name("Category 2").build();
        CategoryItem cat3 = CategoryItem.newBuilder().id(3).name("Category 3").build();
        CategoryItem cat4 = CategoryItem.newBuilder().id(3).name("Category new").build();

        List<CategoryItem> listCat = new ArrayList<CategoryItem>(3);
        listCat.add(cat1);
        listCat.add(cat2);
        listCat.add(cat3);
        listCat.add(cat4);

        emit(new ActionShowCategories(listCat));
    }

    public void getCategoriesOnCreateMovie(){

        CategoryItem cat1 = CategoryItem.newBuilder().id(1).name("Category 1").build();
        CategoryItem cat2 = CategoryItem.newBuilder().id(2).name("Category 2").build();
        CategoryItem cat3 = CategoryItem.newBuilder().id(3).name("Category 3").build();

        List<CategoryItem> listCat = new ArrayList<CategoryItem>(3);
        listCat.add(cat1);
        listCat.add(cat2);
        listCat.add(cat3);

        emit(new ActionOnCreateMovie(listCat));

    }
}
