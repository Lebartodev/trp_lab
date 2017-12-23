import model.CategoryItem;
import model.MovieItem;
import model.data.request.RequestCreateMovie;
import model.data.request.RequestDeleteMovie;
import model.data.request.RequestEndCategoryEdit;
import model.data.request.RequestEndMovieEdit;
import model.data.response.*;
import org.w3c.dom.Document;
import util.MarshallerUtil;
import util.XmlSender;

import javax.xml.transform.TransformerConfigurationException;
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
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DataObject.getFilename()));
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
            System.out.println("Создание файла.");
            File file = new File(DataObject.getFilename());
            if(!file.exists()){
                file.createNewFile();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DataObject.getFilename()));
            System.out.println("Начало сериализации.");
            oos.writeObject(dataObject.getCategories());
            oos.writeObject(dataObject.getMovies());
            oos.writeObject(dataObject.getCategoryId());
            oos.writeObject(dataObject.getFilmId());
            //oos.flush();
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

    static Document getMoviesInCategory(int categoryId, DataObject dataObject) {
        ResponseShowMovieList responseShowMovieList;
        List<MovieItem> movies = new LinkedList<>();
        Map<Integer, MovieItem> movieItemMap = dataObject.getMovies();
        for (Integer integer : movieItemMap.keySet()) {
            if (movieItemMap.get(integer).getGenreId() == categoryId) {
                movies.add(movieItemMap.get(integer));
            }
        }
        responseShowMovieList = new ResponseShowMovieList(movies, dataObject.getCategories().get(categoryId));
        return MarshallerUtil.marshallAction(responseShowMovieList, ResponseShowMovieList.class);
    }

    static void createCategory(String name, DataObject dataObject) {
        CategoryItem categoryItem = CategoryItem.newBuilder().id(dataObject.getCategoryId()).name(name).build();
        dataObject.getCategories().put(categoryItem.getId(), categoryItem);
    }

    static Document deleteCategory(int id, DataObject dataObject) {
        if (dataObject.getLockedCategories().contains(id)) {
            return MarshallerUtil.marshallAction((new ResponseException("This category is already in use.")), ResponseException.class);
        } else {
            dataObject.getCategories().remove(id);
            return MarshallerUtil.marshallAction((new ResponseShowCategories(getCategories(dataObject))), ResponseShowCategories.class);
        }
    }

    static Document lockCategory(int id, DataObject dataObject) {
        if (dataObject.getLockedCategories().contains(id)) {
            ResponseException response = new ResponseException("This category is already in use.");
            return MarshallerUtil.marshallAction(response, ResponseException.class);
        } else {
            ResponseStartCategoryEdit response = new ResponseStartCategoryEdit(dataObject.getCategories().get(id));
            dataObject.getLockedCategories().add(id);
            return MarshallerUtil.marshallAction(response, ResponseStartCategoryEdit.class);
        }
    }

    static void releaseCategory(int id, DataObject dataObject) {
        dataObject.getLockedCategories().remove(dataObject.getLockedCategories().indexOf(id));
    }

    static void changeCategory(Object request, DataObject dataObject) {
        RequestEndCategoryEdit requestEndCategoryEdit = (RequestEndCategoryEdit) request;
        dataObject.getCategories().get(requestEndCategoryEdit.getCategoryId()).setName(requestEndCategoryEdit.getCategoryName());
        dataObject.getLockedCategories().remove(dataObject.getLockedCategories().indexOf(requestEndCategoryEdit.getCategoryId()));
    }

    static Document lockMovie(int id, DataObject dataObject) {
        if (dataObject.getLockedMovies().contains(id)) {
            ResponseException response = new ResponseException("This movie is already in use.");
            return MarshallerUtil.marshallAction(response, ResponseException.class);
        } else {
            ResponseStartMovieEdit response = new ResponseStartMovieEdit(dataObject.getMovies().get(id), getCategories(dataObject));
            dataObject.getLockedMovies().add(id);
            return MarshallerUtil.marshallAction(response, ResponseStartMovieEdit.class);
        }
    }

    static void releaseMovie(int id, DataObject dataObject) {
        dataObject.getLockedMovies().remove(dataObject.getLockedMovies().indexOf(id));
    }

    static void createMovie(Object request, DataObject dataObject) {
        RequestCreateMovie requestCreateMovie = (RequestCreateMovie) request;
        MovieItem newMovie = MovieItem.newBuilder().id(dataObject.getFilmId()).name(requestCreateMovie.getName())
                .description(requestCreateMovie.getDescription()).year(requestCreateMovie.getYear())
                .genreId(requestCreateMovie.getGenreId()).budget(requestCreateMovie.getBudget()).build();
        dataObject.getMovies().put(newMovie.getId(), newMovie);
    }

    static Document changeMovie(Object request, DataObject dataObject) {
        ResponseShowMovieList response;
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
        return MarshallerUtil.marshallAction(response, ResponseShowMovieList.class);
    }

    static Document deleteMovie(Object request, DataObject dataObject){
        RequestDeleteMovie requestDeleteMovie = (RequestDeleteMovie) request;
        if(dataObject.getLockedMovies().contains(requestDeleteMovie.getMovieId())){
            return MarshallerUtil.marshallAction((new ResponseException("This movie is already in use.")), ResponseException.class);
        } else {
            int genreId = dataObject.getMovies().get(requestDeleteMovie.getMovieId()).getGenreId();
            dataObject.getMovies().remove(requestDeleteMovie.getMovieId());
            return getMoviesInCategory(genreId, dataObject);
        }
    }

    static void broadcast(Document response, Map<Integer, Client> clientMap) throws IOException, TransformerConfigurationException {
        for (Integer integer : clientMap.keySet()) {
            Client client = clientMap.get(integer);
            XmlSender.send(response, client.getSocket().getOutputStream());
        }
    }
}
