package main.java;

import main.java.model.data.ActionEmpty;
import main.java.model.data.request.*;
import main.java.model.data.response.ResponseShowCategories;
import main.java.model.data.response.ResponseShowMovie;
import main.java.model.data.response.ResponseShowMovieList;

import java.io.ObjectOutputStream;
import java.util.Map;

class RequestHandler {
    static ActionData handleRequest(ActionData request, DataObject dataObject
            , ObjectOutputStream outputStream, Map<Integer, Client> clientMap) {
        ActionData response = new ActionEmpty();
        try {
            if (request instanceof RequestStartCategoryEdit) {
                response = Operations.lockCategory(((RequestStartCategoryEdit) request).getCategoryId(),dataObject);
                outputStream.writeObject(response);
                outputStream.flush();
            } else if (request instanceof RequestCreateCategory) {
                Operations.createCategory(((RequestCreateCategory) request).getCategoryName(), dataObject);
                response = new ResponseShowCategories(Operations.getCategories(dataObject));
                Operations.broadcast(response, clientMap);
            } else if (request instanceof RequestDeleteCategory) {
                Operations.deleteCategory(((RequestDeleteCategory) request).getId(), dataObject );
                response = new ResponseShowCategories(Operations.getCategories(dataObject));
                Operations.broadcast(response, clientMap);
            } else if (request instanceof RequestStartMovieEdit) {
                
            } else if (request instanceof RequestShowCategories) {
                response = new ResponseShowCategories(Operations.getCategories(dataObject));
                outputStream.writeObject(response);
                outputStream.flush();
            } else if (request instanceof RequestShowMovie) {
                response = new ResponseShowMovie(Operations.
                        getMovie(((RequestShowMovie) request).getMovieId(), dataObject));
                outputStream.writeObject(response);
                outputStream.flush();
            } else if (request instanceof RequestShowMovieList) {
                response = new ResponseShowMovieList(Operations.
                        getMoviesInCategory(((RequestShowMovieList) request).getCategoryId(), dataObject));
                outputStream.writeObject(response);
                outputStream.flush();
            }
        } catch(Exception e){
            System.out.println("error in handler: " + e);
        }

        return response;
    }
}
