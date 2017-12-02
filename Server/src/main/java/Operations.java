package main.java;

import main.java.model.CategoryItem;
import main.java.model.MovieItem;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Operations {
    public static DataObject deserealizeModel(){
        DataObject dataObject = new DataObject();
        File file = new File(DataObject.getFilename());
        if(file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DataObject.getFilename()))) {
                dataObject.setCategories((Map<Integer,CategoryItem>) ois.readObject());
                dataObject.setMovies((Map<Integer,MovieItem>) ois.readObject());
                dataObject.setCategoryId((AtomicInteger) ois.readObject());
                dataObject.setFilmId((AtomicInteger) ois.readObject());
            } catch (Exception ex) {

                ex.printStackTrace();
            }
        } else{
            testData(dataObject);
        }
        return dataObject;
    }

    private static void testData(DataObject dataObject){
        Map<Integer,CategoryItem> categories = new HashMap<>();
        Map<Integer,MovieItem> movies = new HashMap<>();
        for (int i = 0; i < 12; i++) {
            movies.put(i,MovieItem.newBuilder().id(i).name("Test film " + i)
                    .year(1990 + i).description("Test description for Test film " + i)
                    .genreId(Math.round(3*i/12)).budget(500 + i).build());
        }
        for(int i = 0; i<4;i++){
            categories.put(i, CategoryItem.newBuilder().id(i)
                    .name("Category " + i).build());
        }
        AtomicInteger filmId = new AtomicInteger(11);
        AtomicInteger categoryId = new AtomicInteger(3);
        dataObject.setMovies(movies);
        dataObject.setCategories(categories);
        dataObject.setFilmId(filmId);
        dataObject.setCategoryId(categoryId);
    }

    static void serializeModel(DataObject dataObject){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DataObject.getFilename()))) {
            oos.writeObject(dataObject.getCategories());
            oos.writeObject(dataObject.getMovies());
            oos.writeObject(dataObject.getCategoryId());
            oos.writeObject(dataObject.getFilmId());
            System.out.println("Запись произведена");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static List<CategoryItem> getCategories(DataObject dataObject){
        List<CategoryItem> categories = new LinkedList<>();
        Map<Integer, CategoryItem> categoryItemMap = dataObject.getCategories();
        for (Integer integer : categoryItemMap.keySet()) {
            categories.add(categoryItemMap.get(integer));
        }
        return categories;
    }

    static MovieItem getMovie(int id, DataObject dataObject){
        MovieItem resultMovie = MovieItem.newBuilder().build();
        if(dataObject.getMovies().containsKey(id) && !dataObject.getLockedMovies().contains(id)){
            return dataObject.getMovies().get(id);
        }
        return resultMovie;
    }

    static List<MovieItem> getMoviesInCategory(int categoryId, DataObject dataObject){
        List<MovieItem> movies = new LinkedList<>();
        Map<Integer,MovieItem> movieItemMap = dataObject.getMovies();
        for (Integer integer : movieItemMap.keySet()) {
            if(movieItemMap.get(integer).getGenreId() == categoryId){
                movies.add(movieItemMap.get(integer));
            }
        }
        return movies;
    }

    static CategoryItem createCategory(String name, DataObject dataObject){
        CategoryItem categoryItem = CategoryItem.newBuilder().id(id).name(name).build();
        dataObject.getCategories().put()
        return categoryItem;
    }
}
