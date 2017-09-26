package model;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

public class MovieItem implements java.io.Serializable {
    @NotNull
    private int id;
    @Nullable
    private String name;
    @Nullable
    private int year;
    @Nullable
    private String description;
    @NotNull
    private int genreId;
    @Nullable
    private int budget;

    public MovieItem() {

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MovieItem mi = (MovieItem) o;
        return mi.getId() == id &&
                mi.getName().equals(name) &&
                mi.getYear() == year &&
                mi.getGenreId() == genreId &&
                mi.getBudget() == budget;
    }

    @Override
    public int hashCode() {
        return id * 322;
        //TODO change hashcode
    }

    @Override
    public String toString() {
        return "Id: " + id + "/n"
                + "Name: " + name + "/n"
                + "Year: " + year + "/n"
                + "Description: " + description + "/n"
                + "GenreId: " + genreId + "/n"
                + "Budget: " + budget;
    }

}
