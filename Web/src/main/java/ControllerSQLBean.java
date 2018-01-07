import org.hibernate.Session;
import util.CategoryItem;
import util.HibernateUtil;
import util.MDBSender;
import util.MovieItem;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "storage")
public class ControllerSQLBean implements IControllerSQL {

    private static MDBSender mdbSender = new MDBSender();

    public ControllerSQLBean() throws NamingException {
    }

    @Override
    public List<CategoryItem> getCategories() throws SQLException, ClassNotFoundException {
        List<CategoryItem> resultsCategories = new ArrayList<>();
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            resultsCategories = entityManager.createQuery("from CategoryItem", CategoryItem.class).getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultsCategories;
    }

    @Override
    public List<MovieItem> getMoviesInCategory(int categoryId) throws SQLException, ClassNotFoundException {
        List<MovieItem> resultsMovieItem = new ArrayList<>();
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            TypedQuery<MovieItem> query = entityManager.createQuery("select m from MovieItem m where genreId = :id", MovieItem.class);
            resultsMovieItem = query.setParameter("id", categoryId).getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
        return resultsMovieItem;
    }

    @Override
    public CategoryItem getCategory(int id) throws SQLException, ClassNotFoundException {
        CategoryItem categoryItem = null;
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            TypedQuery<CategoryItem> query = entityManager.createQuery("select c from CategoryItem c where id = :id", CategoryItem.class);
            categoryItem = query.setParameter("id", id).getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {

        }
        return categoryItem;
    }

    @Override
    public void updateCategory(CategoryItem categoryItem) throws SQLException, ClassNotFoundException {
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            CategoryItem categoryItem1 = entityManager.find(CategoryItem.class, categoryItem.getId());
            entityManager.getTransaction().begin();
            categoryItem1.setName(categoryItem.getName());
            entityManager.getTransaction().commit();
            entityManager.close();
            mdbSender.sendString(categoryItem.getId(), categoryItem1.getName(), "update");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    @Override
    public void deleteCategory(int categoryId) throws SQLException, ClassNotFoundException {
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            deleteMoviesInCategory(categoryId);
            CategoryItem categoryItem1 = entityManager.find(CategoryItem.class, categoryId);
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("delete from CategoryItem c "
                    + "where c.id = :id");
            query.setParameter("id", categoryId);
            query.executeUpdate();
            entityManager.getTransaction().commit();
            entityManager.close();
            mdbSender.sendString(categoryId,categoryItem1.getName() , "delete");
        } catch (Exception e) {
        }
    }

    @Override
    public void deleteMoviesInCategory(int id) throws SQLException, ClassNotFoundException {
        List<MovieItem> resultsMovieItem;
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            TypedQuery<MovieItem> query1 = entityManager.createQuery("select m from MovieItem m where genreId = :id", MovieItem.class);
            resultsMovieItem = query1.setParameter("id", id).getResultList();
            Query query = entityManager.createQuery("delete from MovieItem m "
                    + "where m.genreId = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            entityManager.getTransaction().commit();
            entityManager.close();
            for (MovieItem movieItem : resultsMovieItem) {
                mdbSender.sendString(movieItem.getId(), movieItem.getName(), "delete");
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void createCategory(CategoryItem categoryItem) throws SQLException, ClassNotFoundException {
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select max(id) from CategoryItem c");
            int maxId = (Integer) query.getSingleResult() + 1;
            entityManager.persist(categoryItem);
            entityManager.getTransaction().commit();
            mdbSender.sendString(maxId, categoryItem.getName(), "create");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    @Override
    public void createMovie(MovieItem movieItem) throws SQLException, ClassNotFoundException {
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select max(m.id) from MovieItem m");
            int maxId = (Integer) query.getSingleResult() + 1;
            entityManager.persist(movieItem);
            entityManager.getTransaction().commit();
            mdbSender.sendString(maxId, movieItem.getName(), "create");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    @Override
    public void deleteMovie(int id) throws SQLException, ClassNotFoundException {
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            MovieItem movieItem = entityManager.find(MovieItem.class, id);
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("delete from MovieItem m "
                    + "where m.id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            entityManager.getTransaction().commit();
            entityManager.close();
            mdbSender.sendString(id, movieItem.getName(), "delete");
        } catch (Exception e) {
        }
    }

    @Override
    public MovieItem getMovie(int id) throws SQLException, ClassNotFoundException {
        MovieItem movieItem = null;
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            TypedQuery<MovieItem> query = entityManager.createQuery("select m from MovieItem m where m.id = :id", MovieItem.class);
            movieItem = query.setParameter("id", id).getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
        }
        return movieItem;
    }

    @Override
    public void updateMovie(MovieItem movieItem) throws SQLException, ClassNotFoundException {
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            MovieItem movieItem1 = entityManager.find(MovieItem.class, movieItem.getId());
            entityManager.getTransaction().begin();
            movieItem1.setName(movieItem.getName());
            entityManager.getTransaction().commit();
            entityManager.close();
            mdbSender.sendString(movieItem.getId(), movieItem1.getName(), "update");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    @Override
    public List<CategoryItem> searchCategory(String name) throws ClassNotFoundException, SQLException {
        List<CategoryItem> resultsCategoryItem = new ArrayList<>();
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            TypedQuery<CategoryItem> query = entityManager.createQuery("select c from CategoryItem c where c.name like :name", CategoryItem.class);
            resultsCategoryItem = query.setParameter("name", "%" + name + "%").getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
        return resultsCategoryItem;
    }

    @Override
    public List<MovieItem> searchMovie(String name) throws ClassNotFoundException, SQLException {
        List<MovieItem> resultsMovieItem = new ArrayList<>();
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            TypedQuery<MovieItem> query = entityManager.createQuery("select m from MovieItem m where m.name like :name", MovieItem.class);
            resultsMovieItem = query.setParameter("name", "%" + name + "%").getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
        return resultsMovieItem;
    }
}
