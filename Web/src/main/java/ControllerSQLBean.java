import util.CategoryItem;
import util.MovieItem;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

@Stateless(name = "storage")
public class ControllerSQLBean implements IControllerSQL{
    // JDBC URL, username and password of MySQL server
    private final String url = "jdbc:mysql://localhost:3306/mydb";
    private final String user = "root";
    private final String password = "953111";

    Context ctx = new InitialContext();
    DataSource dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyLocalDB");

 /*   @Resource(lookup = "java:jdbc/MyLocalDB")
    private DataSource dataSource;*/

    public ControllerSQLBean() throws NamingException {
    }

    @Override
    public ArrayList<CategoryItem> getCategories() throws SQLException, ClassNotFoundException {
        Connection con;
        Class.forName("com.mysql.jdbc.Driver");
        con = dataSource.getConnection();
        String query = "SELECT * FROM Category";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        ArrayList<CategoryItem> categoryItems = new ArrayList<>();
        while (rs.next()) {
            CategoryItem categoryItem = CategoryItem.newBuilder()
                    .id(rs.getInt(1))
                    .name(rs.getString(2))
                    .build();
            categoryItems.add(categoryItem);
        }
        con.close();
        statement.close();
        rs.close();
        return categoryItems;
    }

    @Override
    public ArrayList<MovieItem> getMoviesInCategory(int categoryId) throws SQLException, ClassNotFoundException {
        Connection con;
        Class.forName("com.mysql.jdbc.Driver");
        con = dataSource.getConnection();
        String query = "select * from Movie where Movie.genreId = " + categoryId;
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        ArrayList<MovieItem> movieItems = new ArrayList<>();
        while (rs.next()) {
            MovieItem newMovie = MovieItem.newBuilder().id(rs.getInt(1))
                    .name(rs.getString(2))
                    .year(rs.getInt(3))
                    .description(rs.getString(4))
                    .genreId(rs.getInt(5))
                    .budget(rs.getInt(6))
                    .build();
            movieItems.add(newMovie);
        }
        con.close();
        statement.close();
        rs.close();
        return movieItems;
    }

    @Override
    public CategoryItem getCategory(int id) throws SQLException, ClassNotFoundException {
        Connection con;
        Class.forName("com.mysql.jdbc.Driver");
        con = dataSource.getConnection();
        String query = "select * from Category where id = " + id;
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        CategoryItem categoryItem = CategoryItem.newBuilder().build();
        while (rs.next()) {
            categoryItem.setId(rs.getInt(1));
            categoryItem.setName(rs.getString(2));
        }
        con.close();
        statement.close();
        rs.close();
        return categoryItem;
    }

    @Override
    public void updateCategory(int id, String name) throws SQLException, ClassNotFoundException {
        Connection con;
        Class.forName("com.mysql.jdbc.Driver");
        con = dataSource.getConnection();
        String query = "update Category set name = " + "'" + name + "'" + " where id = " + id;
        Statement statement = con.createStatement();
        statement.executeUpdate(query);
        statement.close();
        con.close();
    }

    @Override
    public void deleteCategory(int id) throws SQLException, ClassNotFoundException {
        Connection con;
        Class.forName("com.mysql.jdbc.Driver");
        deleteMoviesInCategory(id);
        con = dataSource.getConnection();
        String query = "delete from Category where id = " + id;
        Statement statement = con.createStatement();
        statement.executeUpdate(query);
        statement.close();
        con.close();
    }

    @Override
    public void deleteMoviesInCategory(int id) throws SQLException, ClassNotFoundException {
        Connection con;
        Class.forName("com.mysql.jdbc.Driver");
        con = dataSource.getConnection();
        String query = "delete from Movie where genreId = " + id;
        Statement statement = con.createStatement();
        statement.executeUpdate(query);
        statement.close();
        con.close();
    }

    @Override
    public void createCategory(String name) throws SQLException, ClassNotFoundException {
        Connection con;
        Class.forName("com.mysql.jdbc.Driver");
        con = dataSource.getConnection();
        String query = "insert into Category(name) values("
                + "'" + name + "');";
        Statement statement = con.createStatement();
        statement.executeUpdate(query);
        statement.close();
        con.close();
    }

    @Override
    public void createMovie(String name, int year, String description, int genreId, int budget) throws SQLException, ClassNotFoundException {
        Connection con;
        Class.forName("com.mysql.jdbc.Driver");
        con = dataSource.getConnection();
        if(description == null){
            description = "Add some description!";
        }
        String query = "insert into Movie(name, year, description, genreId, budget) values("
                + "'" + name + "'"
                + ", " + year
                + ", '" + description + "'"
                + ", " + genreId
                + ", " + budget + ");";
        Statement statement = con.createStatement();
        statement.executeUpdate(query);
        statement.close();
        con.close();
    }

    @Override
    public void deleteMovie(int id) throws SQLException, ClassNotFoundException {
        Connection con;
        Class.forName("com.mysql.jdbc.Driver");
        con = dataSource.getConnection();
        String query = "delete from Movie where id = " + id;
        Statement statement = con.createStatement();
        statement.executeUpdate(query);
        statement.close();
        con.close();
    }

    @Override
    public MovieItem getMovie(int id) throws SQLException, ClassNotFoundException {
        Connection con;
        Class.forName("com.mysql.jdbc.Driver");
        con = dataSource.getConnection();
        String query = "select * from Movie where id = " + id;
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        MovieItem movieItem = MovieItem.newBuilder().build();
        while (rs.next()) {
            movieItem.setId(rs.getInt(1));
            movieItem.setName(rs.getString(2));
            movieItem.setYear(rs.getInt(3));
            movieItem.setDescription(rs.getString(4));
            movieItem.setGenreId(rs.getInt(5));
            movieItem.setBudget(rs.getInt(6));
        }
        con.close();
        statement.close();
        rs.close();
        return movieItem;
    }

    @Override
    public void updateMovie(int id, String name, int year, String description, int genreId, int budget) throws SQLException, ClassNotFoundException {
        Connection con;
        Class.forName("com.mysql.jdbc.Driver");
        con = dataSource.getConnection();
        if(description == null){
            description = "Add some description!";
        }
        String query = "update Movie set name = " + "'" + name + "'"
                +", year = " + year
                +", description = " + "'" + description + "'"
                +", genreId = " + genreId
                +", budget = " + budget
                + " where id = " + id;
        Statement statement = con.createStatement();
        statement.executeUpdate(query);
        statement.close();
        con.close();
    }

    @Override
    public ArrayList<CategoryItem> searchCategory(String name) throws ClassNotFoundException, SQLException {
        Connection con;
        Class.forName("com.mysql.jdbc.Driver");
        con = dataSource.getConnection();
        String query = "SELECT * FROM Category where name like '%" + name + "%'";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        ArrayList<CategoryItem> categoryItems = new ArrayList<>();
        while (rs.next()) {
            CategoryItem categoryItem = CategoryItem.newBuilder()
                    .id(rs.getInt(1))
                    .name(rs.getString(2))
                    .build();
            categoryItems.add(categoryItem);
        }
        con.close();
        statement.close();
        rs.close();
        return categoryItems;
    }

    @Override
    public ArrayList<MovieItem> searchMovie(String name) throws ClassNotFoundException, SQLException {
        Connection con;
        Class.forName("com.mysql.jdbc.Driver");
        con = dataSource.getConnection();
        String query = "SELECT * FROM Movie where name like '%" + name + "%'";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        ArrayList<MovieItem> movieItems = new ArrayList<>();
        while (rs.next()) {
            MovieItem newMovie = MovieItem.newBuilder().id(rs.getInt(1))
                    .name(rs.getString(2))
                    .year(rs.getInt(3))
                    .description(rs.getString(4))
                    .genreId(rs.getInt(5))
                    .budget(rs.getInt(6))
                    .build();
            movieItems.add(newMovie);
        }
        con.close();
        statement.close();
        rs.close();
        return movieItems;
    }
}
