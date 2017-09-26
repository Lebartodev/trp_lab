package model;

import base.ActionData;
import base.Model;
import model.data.ActionShowCategories;

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
}
