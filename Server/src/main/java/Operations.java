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
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Operations {

    static List<CategoryItem> getCategories() throws SQLException {
        return DBMethods.getCategories();
    }

    static MovieItem getMovie(int id) throws SQLException {
        return DBMethods.getMovie(id);
    }

    static Document getMoviesInCategory(int categoryId) throws SQLException {
        ResponseShowMovieList responseShowMovieList;
        responseShowMovieList = new ResponseShowMovieList(DBMethods.getMoviesInCategory(categoryId)
                , DBMethods.getCategory(categoryId));
        return MarshallerUtil.marshallAction(responseShowMovieList, ResponseShowMovieList.class);
    }

    static void createCategory(String name, DataObject dataObject) throws SQLException {
        CategoryItem categoryItem = CategoryItem.newBuilder().id(dataObject.getCategoryId()).name(name).build();
        DBMethods.createCategory(categoryItem);
    }

    static Document deleteCategory(int id, DataObject dataObject) throws SQLException {
        if (dataObject.getLockedCategories().contains(id)) {
            return MarshallerUtil.marshallAction((new ResponseException("This category is already in use.")), ResponseException.class);
        } else {
            DBMethods.deleteCategory(id);
            return MarshallerUtil.marshallAction((new ResponseShowCategories(DBMethods.getCategories())), ResponseShowCategories.class);
        }
    }

    static Document lockCategory(int id, DataObject dataObject) throws SQLException {
        if (dataObject.getLockedCategories().contains(id)) {
            ResponseException response = new ResponseException("This category is already in use.");
            return MarshallerUtil.marshallAction(response, ResponseException.class);
        } else {
            ResponseStartCategoryEdit response = null;
            response = new ResponseStartCategoryEdit(DBMethods.getCategory(id));
            dataObject.getLockedCategories().add(id);
            return MarshallerUtil.marshallAction(response, ResponseStartCategoryEdit.class);
        }
    }

    static void releaseCategory(int id, DataObject dataObject) {
        dataObject.getLockedCategories().remove(dataObject.getLockedCategories().indexOf(id));
    }

    static void changeCategory(Object request, DataObject dataObject) throws SQLException {
        RequestEndCategoryEdit requestEndCategoryEdit = (RequestEndCategoryEdit) request;
        DBMethods.updateCategory(CategoryItem.newBuilder()
                .id(requestEndCategoryEdit.getCategoryId())
                .name(requestEndCategoryEdit.getCategoryName())
                .build());
        dataObject.getLockedCategories().remove(dataObject.getLockedCategories().indexOf(requestEndCategoryEdit.getCategoryId()));
    }

    static Document lockMovie(int id, DataObject dataObject) throws SQLException {
        if (dataObject.getLockedMovies().contains(id)) {
            ResponseException response = new ResponseException("This movie is already in use.");
            return MarshallerUtil.marshallAction(response, ResponseException.class);
        } else {
            ResponseStartMovieEdit response = new ResponseStartMovieEdit(DBMethods.getMovie(id), getCategories());
            dataObject.getLockedMovies().add(id);
            return MarshallerUtil.marshallAction(response, ResponseStartMovieEdit.class);
        }
    }

    static void releaseMovie(int id, DataObject dataObject) {
        dataObject.getLockedMovies().remove(dataObject.getLockedMovies().indexOf(id));
    }

    static void createMovie(Object request, DataObject dataObject) throws SQLException {
        RequestCreateMovie requestCreateMovie = (RequestCreateMovie) request;
        MovieItem newMovie = MovieItem.newBuilder().id(dataObject.getFilmId()).name(requestCreateMovie.getName())
                .description(requestCreateMovie.getDescription()).year(requestCreateMovie.getYear())
                .genreId(requestCreateMovie.getGenreId()).budget(requestCreateMovie.getBudget()).build();
        DBMethods.createMovie(newMovie);
    }

    static Document changeMovie(Object request, DataObject dataObject) throws SQLException {
        ResponseShowMovieList response;
        RequestEndMovieEdit requestEndMovieEdit = (RequestEndMovieEdit) request;
        MovieItem changedMovie = DBMethods.getMovie(requestEndMovieEdit.getId());
        int oldCategory = changedMovie.getGenreId();
        changedMovie.setName(requestEndMovieEdit.getName());
        changedMovie.setYear(requestEndMovieEdit.getYear());
        changedMovie.setDescription(requestEndMovieEdit.getDescription());
        changedMovie.setBudget(requestEndMovieEdit.getBudget());
        changedMovie.setGenreId(requestEndMovieEdit.getGenreId());
        dataObject.getLockedMovies().remove(dataObject.getLockedMovies().indexOf(changedMovie.getId()));
        DBMethods.updateMovie(changedMovie);
        response = new ResponseShowMovieList(DBMethods.getCategory(changedMovie.getGenreId())
                , DBMethods.getMoviesInCategory(changedMovie.getGenreId()), DBMethods.getCategory(oldCategory));
        return MarshallerUtil.marshallAction(response, ResponseShowMovieList.class);
    }

    static Document deleteMovie(Object request, DataObject dataObject) throws SQLException {
        RequestDeleteMovie requestDeleteMovie = (RequestDeleteMovie) request;
        if (dataObject.getLockedMovies().contains(requestDeleteMovie.getMovieId())) {
            return MarshallerUtil.marshallAction((new ResponseException("This movie is already in use.")), ResponseException.class);
        } else {
            int genreId = DBMethods.getMovie(requestDeleteMovie.getMovieId()).getGenreId();
            DBMethods.deleteMovie(requestDeleteMovie.getMovieId());
            return getMoviesInCategory(genreId);
        }
    }

    static void broadcast(Document response, Map<Integer, Client> clientMap) throws IOException, TransformerConfigurationException {
        for (Integer integer : clientMap.keySet()) {
            Client client = clientMap.get(integer);
            XmlSender.send(response, client.getSocket().getOutputStream());
        }
    }
}
