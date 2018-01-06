package util;

import model.Task;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sasha on 14.03.2016.
 */
public class TaskDAO implements ITaskDAO {

    public void addTask(Task task) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            if (session != null && session.isOpen()) {

                session.close();
            }

        }
    }
    public List<Task> getAllTasks() {
        Session session = null;
        List<Task> resultsTasks = new ArrayList<Task>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            resultsTasks = (List<Task>)session.createCriteria(Task.class).list();
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            if (session != null && session.isOpen()) {

                session.close();
            }

        }
        return resultsTasks;
    }

    public void updateTask(Task task) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public List<Task> getTasksEmploes(User user){
        Session session = null;
        List<User> resultsUser;
        List<Task> res = new ArrayList<Task>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "from User where boss = :id";
            Query query = session.createQuery(hql);
            query.setString("id",user.getId().toString());
            resultsUser =(List<User>) query.list();
            for(User u : resultsUser){
                res.addAll(u.getTasks());
            }
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            if (session != null && session.isOpen()) {

                session.close();
            }

        }
        return res;
    }

    public Task getTaskById(int task_id) {
        Session session = null;
        Task task= null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "from Task where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id",task_id);
            task = (Task) query.uniqueResult();
        } catch (Exception e) {

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return task;
    }


    public void deleteTask(Task task) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(task);
            session.getTransaction().commit();
        } catch (Exception e) {
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

    @Override
    public void deleteTask(int id) {

    }
}
