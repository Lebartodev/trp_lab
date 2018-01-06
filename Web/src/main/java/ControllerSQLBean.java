import org.hibernate.Query;
import org.hibernate.Session;
import util.CategoryItem;
import util.HibernateUtil;
import util.MovieItem;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "storage")
public class ControllerSQLBean implements IControllerSQL{

    public ControllerSQLBean() throws NamingException {
    }

    @Override
    public List<CategoryItem> getCategories() throws SQLException, ClassNotFoundException {
        Session session = null;
        List<CategoryItem> resultsCategories = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            resultsCategories = (List<CategoryItem>)session.createCriteria(CategoryItem.class).list();
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            if (session != null && session.isOpen()) {

                session.close();
            }

        }
        return resultsCategories;
    }

    @Override
    public List<MovieItem> getMoviesInCategory(int categoryId) throws SQLException, ClassNotFoundException {
        Session session = null;
        List<MovieItem> resultsMovieItem = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "from MovieItem where genreId = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id",categoryId);
            resultsMovieItem = (List<MovieItem>) query.list();
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            if (session != null && session.isOpen()) {

                session.close();
            }

        }
        return resultsMovieItem;
    }

    @Override
    public CategoryItem getCategory(int id) throws SQLException, ClassNotFoundException {
        Session session = null;
        CategoryItem categoryItem = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "from CategoryItem where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id",id);
            categoryItem = (CategoryItem) query.uniqueResult();
        } catch (Exception e) {

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return categoryItem;
    }

    @Override
    public void updateCategory(CategoryItem categoryItem) throws SQLException, ClassNotFoundException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(categoryItem);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void deleteCategory(int categoryId) throws SQLException, ClassNotFoundException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "delete from CategoryItem where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", categoryId);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void deleteMoviesInCategory(int id) throws SQLException, ClassNotFoundException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "delete from MovieItem where genreId = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void createCategory(CategoryItem categoryItem) throws SQLException, ClassNotFoundException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(categoryItem);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            if (session != null && session.isOpen()) {

                session.close();
            }
        }
    }

    @Override
    public void createMovie(MovieItem movieItem) throws SQLException, ClassNotFoundException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(movieItem);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            if (session != null && session.isOpen()) {

                session.close();
            }

        }
    }

    @Override
    public void deleteMovie(int id) throws SQLException, ClassNotFoundException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "delete from MovieItem where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public MovieItem getMovie(int id) throws SQLException, ClassNotFoundException {
        Session session = null;
        MovieItem movieItem = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "from MovieItem where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id",id);
            movieItem = (MovieItem) query.uniqueResult();
        } catch (Exception e) {

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return movieItem;
    }

    @Override
    public void updateMovie(MovieItem movieItem) throws SQLException, ClassNotFoundException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(movieItem);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<CategoryItem> searchCategory(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<MovieItem> searchMovie(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    /*@Override
    public List<CategoryItem> searchCategory(String name) throws ClassNotFoundException, SQLException {
        Session session = null;
        List<MovieItem> resultsMovieItem = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "from CategoryItem where name like ";
            Query query = session.createQuery(hql);
            query.setInteger("id",categoryId);
            resultsMovieItem = (List<MovieItem>) query.list();
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            if (session != null && session.isOpen()) {

                session.close();
            }

        }
        return resultsMovieItem;
    }

    @Override
    public List<MovieItem> searchMovie(String name) throws ClassNotFoundException, SQLException {
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
    }*/
}
