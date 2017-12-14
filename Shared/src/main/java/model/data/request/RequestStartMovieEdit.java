package main.java.model.data.request;


public class RequestStartMovieEdit  {
    private int movieId;


    public RequestStartMovieEdit(int movieId) {
        this.movieId = movieId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
