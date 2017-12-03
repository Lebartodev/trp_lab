package main.java;

import main.java.model.CategoryItem;
import main.java.model.MovieItem;
import main.java.model.data.request.RequestCreateMovie;
import main.java.model.data.request.RequestDeleteMovie;
import main.java.model.data.request.RequestEndCategoryEdit;
import main.java.model.data.request.RequestEndMovieEdit;
import main.java.model.data.response.*;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Operations {
    public static DataObject deserealizeModel() {
        DataObject dataObject = new DataObject();
        File file = new File(DataObject.getFilename());
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DataObject.getFilename()))) {
                dataObject.setCategories((Map<Integer, CategoryItem>) ois.readObject());
                dataObject.setMovies((Map<Integer, MovieItem>) ois.readObject());
                dataObject.setCategoryId((Integer) ois.readObject());
                dataObject.setFilmId((Integer) ois.readObject());
            } catch (Exception ex) {

                ex.printStackTrace();
            }
        } else {
            testData(dataObject);
        }
        return dataObject;
    }

    private static void testData(DataObject dataObject) {
        Map<Integer, CategoryItem> categories = new HashMap<>();
        Map<Integer, MovieItem> movies = new HashMap<>();
        for (int i = 0; i < 12; i++) {
            movies.put(i, MovieItem.newBuilder().id(i).name("Test film " + i)
                    .year(1990 + i).description("Test description for Test film " + i)
                    .genreId(Math.round(3 * i / 12)).budget(500 + i).build());
        }
        for (int i = 0; i < 4; i++) {
            categories.put(i, CategoryItem.newBuilder().id(i)
                    .name("Category " + i).build());
        }
        int filmId = 11;
        int categoryId = 3;
        dataObject.setMovies(movies);
        dataObject.setCategories(categories);
        dataObject.setFilmId(filmId);
        dataObject.setCategoryId(categoryId);
    }

    static void serializeModel(DataObject dataObject) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DataObject.getFilename()));
            oos.writeObject(dataObject.getCategories());
            oos.writeObject(dataObject.getMovies());
            oos.writeObject(dataObject.getCategoryId());
            oos.writeObject(dataObject.getFilmId());
            System.out.println("Запись произведена");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static List<CategoryItem> getCategories(DataObject dataObject) {
        List<CategoryItem> categories = new LinkedList<>();
        Map<Integer, CategoryItem> categoryItemMap = dataObject.getCategories();
        for (Integer integer : categoryItemMap.keySet()) {
            categories.add(categoryItemMap.get(integer));
        }
        return categories;
    }

    static MovieItem getMovie(int id, DataObject dataObject) {
        return dataObject.getMovies().get(id);
    }

    static ActionData getMoviesInCategory(int categoryId, DataObject dataObject) {
        ResponseShowMovieList responseShowMovieList;
        List<MovieItem> movies = new LinkedList<>();
        Map<Integer, MovieItem> movieItemMap = dataObject.getMovies();
        for (Integer integer : movieItemMap.keySet()) {
            if (movieItemMap.get(integer).getGenreId() == categoryId) {
                movies.add(movieItemMap.get(integer));
            }
        }
        responseShowMovieList = new ResponseShowMovieList(movies, dataObject.getCategories().get(categoryId));
        return responseShowMovieList;
    }

    static void createCategory(String name, DataObject dataObject) {
        CategoryItem categoryItem = CategoryItem.newBuilder().id(dataObject.getCategoryId()).name(name).build();
        dataObject.getCategories().put(categoryItem.getId(), categoryItem);
    }

    static ActionData deleteCategory(int id, DataObject dataObject) {
        if (dataObject.getLockedCategories().contains(id)) {
            return new ResponseException(new Exception("This category is already in use."));
        } else {
            dataObject.getCategories().remove(id);
            return new ResponseShowCategories(getCategories(dataObject));
        }
    }

    static ActionData lockCategory(int id, DataObject dataObject) {
        ActionData response;
        if (dataObject.getLockedCategories().contains(id)) {
            response = new ResponseException(new Exception("This category is already in use."));
        } else {
            response = new ResponseStartCategoryEdit(dataObject.getCategories().get(id));
            dataObject.getLockedCategories().add(id);
        }
        return response;
    }

    static void releaseCategory(int id, DataObject dataObject) {
        dataObject.getLockedCategories().remove(dataObject.getLockedCategories().indexOf(id));
    }

    static void changeCategory(ActionData request, DataObject dataObject) {
        RequestEndCategoryEdit requestEndCategoryEdit = (RequestEndCategoryEdit) request;
        dataObject.getCategories().get(requestEndCategoryEdit.getCategoryId()).setName(requestEndCategoryEdit.getCategoryName());
        dataObject.getLockedCategories().remove(dataObject.getLockedCategories().indexOf(requestEndCategoryEdit.getCategoryId()));
    }

    static ActionData lockMovie(int id, DataObject dataObject) {
        ActionData response;
        if (dataObject.getLockedMovies().contains(id)) {
            response = new ResponseException(new Exception("This movie is already in use."));
        } else {
            response = new ResponseStartMovieEdit(dataObject.getMovies().get(id), getCategories(dataObject));
            dataObject.getLockedMovies().add(id);
        }
        return response;
    }

    static void releaseMovie(int id, DataObject dataObject) {
        dataObject.getLockedMovies().remove(dataObject.getLockedMovies().indexOf(id));
    }

    static void createMovie(ActionData request, DataObject dataObject) {
        RequestCreateMovie requestCreateMovie = (RequestCreateMovie) request;
        MovieItem newMovie = MovieItem.newBuilder().id(dataObject.getFilmId()).name(requestCreateMovie.getName())
                .description(requestCreateMovie.getDescription()).year(requestCreateMovie.getYear())
                .genreId(requestCreateMovie.getGenreId()).budget(requestCreateMovie.getBudget()).build();
        dataObject.getMovies().put(newMovie.getId(), newMovie);
    }

    static ActionData changeMovie(ActionData request, DataObject dataObject) {
        ActionData response;
        RequestEndMovieEdit requestEndMovieEdit = (RequestEndMovieEdit) request;
        int oldCategory = dataObject.getCategories().get(dataObject.getMovies().get(requestEndMovieEdit.getId()).getGenreId()).getId();
        MovieItem changedMovie = dataObject.getMovies().get(requestEndMovieEdit.getId());
        changedMovie.setName(requestEndMovieEdit.getName());
        changedMovie.setYear(requestEndMovieEdit.getYear());
        changedMovie.setDescription(requestEndMovieEdit.getDescription());
        changedMovie.setBudget(requestEndMovieEdit.getBudget());
        changedMovie.setGenreId(requestEndMovieEdit.getGenreId());
        dataObject.getLockedMovies().remove(dataObject.getLockedMovies().indexOf(changedMovie.getId()));
        List<MovieItem> movies = new LinkedList<>();
        Map<Integer, MovieItem> movieItemMap = dataObject.getMovies();
        for (Integer integer : movieItemMap.keySet()) {
            if (movieItemMap.get(integer).getGenreId() == changedMovie.getGenreId()) {
                movies.add(movieItemMap.get(integer));
            }
        }
        response = new ResponseShowMovieList(dataObject.getCategories().get(changedMovie.getGenreId()), movies, dataObject.getCategories().get(oldCategory));
        return response;
    }

    static ActionData deleteMovie(ActionData request, DataObject dataObject){
        RequestDeleteMovie requestDeleteMovie = (RequestDeleteMovie) request;
        ActionData response;
        if(dataObject.getLockedMovies().contains(requestDeleteMovie.getMovieId())){
            response = new ResponseException(new Exception("This movie is already in use."));
            return response;
        } else {
            int genreId = dataObject.getMovies().get(requestDeleteMovie.getMovieId()).getGenreId();
            dataObject.getMovies().remove(requestDeleteMovie.getMovieId());
            response = getMoviesInCategory(genreId, dataObject);
            return response;
        }
    }

    static void broadcast(ActionData response, Map<Integer, Client> clientMap) throws IOException {
        for (Integer integer : clientMap.keySet()) {
            Client client = clientMap.get(integer);
            client.getObjectOutputStream().reset();
            client.getObjectOutputStream().writeObject(response);
            client.getObjectOutputStream().flush();
        }
    }
}
