package model;

import base.ActionData;
import base.Model;
import model.data.ActionShowCategories;
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
            movies.add(MovieItem.newBuilder().id(i).name("Test film " + i)
                    .year(1990 + i).description("Test description for Test film " + i)
            .genreId(id).budget(500 + i).build());
        }
        
        emit(new ActionShowMoviesInCategory(CategoryItem.newBuilder().id(id).name("Test category " + id)
                .movies(movies).build()));
    }
}
