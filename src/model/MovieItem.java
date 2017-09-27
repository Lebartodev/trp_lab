package model;


public class MovieItem implements java.io.Serializable {
    private int id;
    private String name;
    private int year;
    private String description;
    private int genreId;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieItem movieItem = (MovieItem) o;

        if (id != movieItem.id) return false;
        if (year != movieItem.year) return false;
        if (genreId != movieItem.genreId) return false;
        if (budget != movieItem.budget) return false;
        return name != null ? name.equals(movieItem.name) : movieItem.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + genreId;
        result = 31 * result + budget;
        return result;
    }

    @Override
    public String toString() {
        return "MovieItem{" + System.lineSeparator() +
                "id=" + id + System.lineSeparator() +
                ", name='" + name + '\'' + System.lineSeparator() +
                ", year=" + year + System.lineSeparator() +
                ", description='" + description + '\'' + System.lineSeparator() +
                ", genreId=" + genreId + System.lineSeparator() +
                ", budget=" + budget + System.lineSeparator() +
                '}';
    }

    public static Builder newBuilder() {
        return new MovieItem().new Builder();
    }

    public class Builder{
        private Builder(){

        }

        public Builder id(int id){
            MovieItem.this.id = id;
            return this;
        }

        public Builder name(String name){
            if(name == null){
                MovieItem.this.name = "New Film";
            } else {
                MovieItem.this.name = name;
            }
            return this;
        }

        public Builder year(int year){
            MovieItem.this.year = year;
            return this;
        }

        public Builder description(String description){
            if(description == null){
                MovieItem.this.description = "Add description to this movie!";
            } else {
                MovieItem.this.description = description;
            }
            return this;
        }

        public Builder genreId(int genreId){
            MovieItem.this.genreId = genreId;
            return this;
        }

        public Builder budget(int budget){
            MovieItem.this.budget = budget;
            return this;
        }

        public MovieItem build(){
            return MovieItem.this;
        }
    }

}
