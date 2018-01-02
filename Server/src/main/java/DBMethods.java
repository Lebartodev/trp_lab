import model.CategoryItem;
import model.MovieItem;

import java.sql.*;
import java.util.ArrayList;

public class DBMethods {

    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/mydb";
    private static final String user = "root";
    private static final String password = "953111";
    // JDBC variables for opening and managing connection
    private static Connection con;

    public static ArrayList<MovieItem> getMoviesInCategory(int categoryId) throws SQLException {
        con = DriverManager.getConnection(url, user, password);
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

    public static ArrayList<MovieItem> getMovies() throws SQLException {
        con = DriverManager.getConnection(url, user, password);
        String query = "SELECT * FROM Movie";
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

    public static MovieItem getMovie(int id) throws SQLException {
        con = DriverManager.getConnection(url, user, password);
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

    public static ArrayList<CategoryItem> getCategories() throws SQLException {
        con = DriverManager.getConnection(url, user, password);
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

    public static CategoryItem getCategory(int id) throws SQLException {
        con = DriverManager.getConnection(url, user, password);
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

    public static int getFilmId() throws SQLException {
        con = DriverManager.getConnection(url, user, password);
        String query = "SELECT max(id) FROM Movie";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        int filmId = 1;
        while (rs.next()) {
            filmId = rs.getInt(1);
        }
        con.close();
        statement.close();
        rs.close();
        return filmId;
    }

    public static int getCategoryId() throws SQLException {
        con = DriverManager.getConnection(url, user, password);
        String query = "SELECT max(id) FROM Category";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        int categoryId = 1;
        while (rs.next()) {
            categoryId = rs.getInt(1);
        }
        con.close();
        statement.close();
        rs.close();
        return categoryId;
    }

    public static DataObject getModel() throws SQLException {
        DataObject dataObject = new DataObject();
        dataObject.setCategoryId(getCategoryId());
        dataObject.setFilmId(getFilmId());
        dataObject.setLockedCategories(new ArrayList<Integer>());
        dataObject.setLockedMovies(new ArrayList<Integer>());
        return dataObject;
    }

    public static void updateMovie(MovieItem movieItem) throws SQLException {
        con = DriverManager.getConnection(url, user, password);
        String query = "update Movie set name = " + "'" + movieItem.getName() + "'"
                +", year = " + movieItem.getYear()
                +", description = " + "'" + movieItem.getDescription() + "'"
                +", genreId = " + movieItem.getGenreId()
                +", budget = " + movieItem.getBudget()
                + " where id = " + movieItem.getId();
        Statement statement = con.createStatement();
        statement.executeUpdate(query);
        statement.close();
        con.close();
    }

    public static void deleteMoviesInCategory(int id) throws SQLException {
        con = DriverManager.getConnection(url, user, password);
        String query = "delete from Movie where genreId = " + id;
        Statement statement = con.createStatement();
        statement.executeUpdate(query);
        statement.close();
        con.close();
    }

    public static void updateCategory(CategoryItem categoryItem) throws SQLException {
        con = DriverManager.getConnection(url, user, password);
        String query = "update Category set name = " + "'" + categoryItem.getName() + "'" + " where id = " + categoryItem.getId();
        Statement statement = con.createStatement();
        statement.executeUpdate(query);
        statement.close();
        con.close();
    }

    public static void deleteCategory(int id) throws SQLException {
        deleteMoviesInCategory(id);
        con = DriverManager.getConnection(url, user, password);
        String query = "delete from Category where id = " + id;
        Statement statement = con.createStatement();
        statement.executeUpdate(query);
        statement.close();
        con.close();
    }

    public static void deleteMovie(int id) throws SQLException {
        con = DriverManager.getConnection(url, user, password);
        String query = "delete from Movie where id = " + id;
        Statement statement = con.createStatement();
        statement.executeUpdate(query);
        statement.close();
        con.close();
    }

    public static void createMovie(MovieItem movieItem) throws SQLException {
        con = DriverManager.getConnection(url, user, password);
        String query = "insert into Movie(id, name, year, description, genreId, budget) values("
                + movieItem.getId() + ", '" + movieItem.getName() + "'"
                + ", " + movieItem.getYear()
                + ", '" + movieItem.getDescription() + "'"
                + ", " + movieItem.getGenreId()
                + ", " + movieItem.getBudget() + ");";
        Statement statement = con.createStatement();
        statement.executeUpdate(query);
        statement.close();
        con.close();
    }

    public static void createCategory(CategoryItem categoryItem) throws SQLException {
        con = DriverManager.getConnection(url, user, password);
        String query = "insert into Category(id, name) values("
                + categoryItem.getId()
                + ", '" + categoryItem.getName() + "');";
        Statement statement = con.createStatement();
        statement.executeUpdate(query);
        statement.close();
        con.close();
    }

}
