import util.MovieItem;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@ManagedBean
@RequestScoped
public class MoviePage {
    @EJB(lookup = "java:module/storage")
    private IControllerSQL controllerSQL;

    private int selectedId;
    private MovieItem selectedMovie;

    public Collection<MovieItem> getMovies(int id) throws SQLException, ClassNotFoundException {
        return controllerSQL.getMoviesInCategory(id);
    }

    public void loadMovie() {
        try {
            selectedMovie = controllerSQL.getMovie(selectedId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MovieItem getSelectedMovie() {
        return selectedMovie;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }

    public int getSelectedId() {
        return selectedId;
    }

    public void createMovie(String name, int year, String description, int genreId, int budget){
        try {
            controllerSQL.createMovie(MovieItem.newBuilder().name(name).year(year).description(description).genreId(genreId).budget(budget).build());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("movieList.jsf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteMovie(int id){
        try {
            controllerSQL.deleteMovie(id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("movieList.jsf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateMovie(int id, String name, int year, String description, int genreId, int budget){
        try {
            controllerSQL.updateMovie(MovieItem.newBuilder().id(id).name(name).year(year).description(description).genreId(genreId).budget(budget).build());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("movieList.jsf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
