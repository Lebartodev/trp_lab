import util.CategoryItem;
import util.MovieItem;

import javax.ejb.Remote;
import java.sql.SQLException;
import java.util.ArrayList;

@Remote
public interface IControllerSQL {

    public ArrayList<CategoryItem> getCategories() throws SQLException, ClassNotFoundException;

    public ArrayList<MovieItem> getMoviesInCategory(int categoryId) throws SQLException, ClassNotFoundException;

    public CategoryItem getCategory(int id) throws SQLException, ClassNotFoundException;

    public void updateCategory(int id, String name) throws SQLException, ClassNotFoundException;

    public void deleteCategory(int id) throws SQLException, ClassNotFoundException;

    public void deleteMoviesInCategory(int id) throws SQLException, ClassNotFoundException;

    public void createCategory(String name) throws SQLException, ClassNotFoundException;

    public void createMovie(String name, int year, String description, int genreId, int budget) throws SQLException, ClassNotFoundException;

    public void deleteMovie(int id) throws SQLException, ClassNotFoundException;

    public MovieItem getMovie(int id) throws SQLException, ClassNotFoundException;

    public void updateMovie(int id, String name, int year, String description, int genreId, int budget) throws SQLException, ClassNotFoundException;

    public ArrayList<CategoryItem> searchCategory(String name) throws SQLException, ClassNotFoundException;

    public ArrayList<MovieItem> searchMovie(String name) throws SQLException, ClassNotFoundException;
}
