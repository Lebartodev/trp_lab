import util.CategoryItem;
import util.MovieItem;

import javax.ejb.Remote;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Remote
public interface IControllerSQL {

    public List<CategoryItem> getCategories() throws SQLException, ClassNotFoundException;

    public List<MovieItem> getMoviesInCategory(int categoryId) throws SQLException, ClassNotFoundException;

    public CategoryItem getCategory(int id) throws SQLException, ClassNotFoundException;

    public void updateCategory(CategoryItem categoryItem) throws SQLException, ClassNotFoundException;

    public void deleteCategory(int id) throws SQLException, ClassNotFoundException;

    public void deleteMoviesInCategory(int id) throws SQLException, ClassNotFoundException;

    public void createCategory(CategoryItem categoryItem) throws SQLException, ClassNotFoundException;

    public void createMovie(MovieItem movieItem) throws SQLException, ClassNotFoundException;

    public void deleteMovie(int id) throws SQLException, ClassNotFoundException;

    public MovieItem getMovie(int id) throws SQLException, ClassNotFoundException;

    public void updateMovie(MovieItem movieItem) throws SQLException, ClassNotFoundException;

    public List<CategoryItem> searchCategory(String name) throws SQLException, ClassNotFoundException;

    public List<MovieItem> searchMovie(String name) throws SQLException, ClassNotFoundException;
}
