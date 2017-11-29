package main.java;

import main.java.model.data.ActionEmpty;
import main.java.model.data.request.*;
import main.java.model.data.response.ResponseShowCategories;

public class RequestHandler {
    public static ActionData handleRequest(ActionData request, DataObject dataObject){
        ActionData response = new ActionEmpty();

        if(request instanceof RequestCategoryEdit){

        } else if(request instanceof RequestCreateCategory){

        } else if(request instanceof RequestDeleteCategory){

        } else if(request instanceof RequestMovieEdit){

        } else if(request instanceof RequestShowCategories){
            response = new ResponseShowCategories(Operations.getCategories(dataObject));

        } else if(request instanceof RequestShowMovie){

        } else if(request instanceof RequestShowMovieList){

        } else if(request instanceof RequestUpdateCategories){

        } else if(request instanceof RequestUpdateMovies){

        }
        return response;
    }
}
