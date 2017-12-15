package model.data.request;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"id", "name", "year", "description", "genreId", "budget"}, name = "requestEndMovieEdit")
public class RequestEndMovieEdit {
    private int id;
    private String name;
    private int year;
    private String description;
    private int genreId;
    private int budget;

    public RequestEndMovieEdit() {
    }

    public RequestEndMovieEdit(int id, String name, int year, String description, int genreId, int budget) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.description = description;
        this.genreId = genreId;
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
}
