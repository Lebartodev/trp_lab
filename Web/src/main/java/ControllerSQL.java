import model.CategoryItem;
import model.MovieItem;

import java.sql.*;
import java.util.ArrayList;

public class ControllerSQL {
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/mydb";
    private static final String user = "root";
    private static final String password = "953111";
    // JDBC variables for opening and managing connection
    private static Connection con;

    public static ArrayList<CategoryItem> getCategories() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
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

    public static ArrayList<MovieItem> getMoviesInCategory(int categoryId) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
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
}
