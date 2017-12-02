package main.java;

import main.java.model.data.ActionEmpty;
import main.java.model.data.request.*;
import main.java.model.data.response.ResponseCreateCategory;
import main.java.model.data.response.ResponseShowCategories;
import main.java.model.data.response.ResponseShowMovie;
import main.java.model.data.response.ResponseShowMovieList;

public class RequestHandler {
    public static ActionData handleRequest(ActionData request, DataObject dataObject){
        ActionData response = new ActionEmpty();

        if(request instanceof RequestStartCategoryEdit){

        } else if(request instanceof RequestCreateCategory){
            response = new ResponseCreateCategory();

        } else if(request instanceof RequestDeleteCategory){

        } else if(request instanceof RequestStartMovieEdit){

        } else if(request instanceof RequestShowCategories){
            response = new ResponseShowCategories(Operations.getCategories(dataObject));
        } else if(request instanceof RequestShowMovie){
            response = new ResponseShowMovie(Operations.getMovie(((RequestShowMovie) request).getMovieId(),dataObject));
        } else if(request instanceof RequestShowMovieList){
            response = new ResponseShowMovieList(Operations.getMoviesInCategory(((RequestShowMovieList) request).getCategoryId(),dataObject));
        } else if(request instanceof RequestUpdateCategories){

        } else if(request instanceof RequestUpdateMovies){

        }
        return response;
    }
}
