package main.java;

import main.java.model.data.ActionEmpty;
import main.java.model.data.request.*;
import main.java.model.data.response.ResponseShowCategories;
import main.java.model.data.response.ResponseShowMovie;
import main.java.model.data.response.ResponseShowMovieList;
import main.java.model.data.response.ResponseStartMovieEdit;

import java.io.ObjectOutputStream;
import java.util.Map;

class RequestHandler {
    static ActionData handleRequest(ActionData request, DataObject dataObject
            , ObjectOutputStream outputStream, Map<Integer, Client> clientMap) {
        ActionData response = new ActionEmpty();
        try {
            if (request instanceof RequestStartCategoryEdit) {
                response = Operations.lockCategory(((RequestStartCategoryEdit) request).getCategoryId(), dataObject);
                outputStream.reset();
                outputStream.writeObject(response);
                outputStream.flush();
            } else if (request instanceof RequestEndCategoryEdit) {
                if (((RequestEndCategoryEdit) request).getCategoryName() == null) {
                    Operations.releaseCategory(((RequestEndCategoryEdit) request).getCategoryId(), dataObject);
                } else {
                    Operations.changeCategory(request, dataObject);
                    response = new ResponseShowCategories(Operations.getCategories(dataObject));
                    Operations.broadcast(response, clientMap);
                }
            } else if (request instanceof RequestCreateCategory) {
                Operations.createCategory(((RequestCreateCategory) request).getCategoryName(), dataObject);
                response = new ResponseShowCategories(Operations.getCategories(dataObject));
                Operations.broadcast(response, clientMap);
            } else if (request instanceof RequestDeleteCategory) {
                response = Operations.deleteCategory(((RequestDeleteCategory) request).getId(), dataObject);
                Operations.broadcast(response, clientMap);
            } else if (request instanceof RequestStartMovieEdit) {
                response = Operations.lockMovie(((RequestStartMovieEdit) request).getMovieId(), dataObject);
                outputStream.reset();
                outputStream.writeObject(response);
                outputStream.flush();
            } else if (request instanceof RequestShowCategories) {
                response = new ResponseShowCategories(Operations.getCategories(dataObject));
                outputStream.reset();
                outputStream.writeObject(response);
                outputStream.flush();
            } else if (request instanceof RequestShowMovie) {
                response = new ResponseShowMovie(Operations.
                        getMovie(((RequestShowMovie) request).getMovieId(), dataObject));
                outputStream.reset();
                outputStream.writeObject(response);
                outputStream.flush();
            } else if (request instanceof RequestShowMovieList) {
                response = Operations.getMoviesInCategory(((RequestShowMovieList) request).getCategoryId(), dataObject);
                outputStream.reset();
                outputStream.writeObject(response);
                outputStream.flush();
            } else if (request instanceof RequestEndMovieEdit) {
                if (((RequestEndMovieEdit) request).getName() == null) {
                    Operations.releaseMovie(((RequestEndMovieEdit) request).getId(), dataObject);
                } else {
                    Operations.changeMovie(request, dataObject);
                    response = Operations.getMoviesInCategory(((RequestEndMovieEdit) request).getGenreId(), dataObject);
                    Operations.broadcast(response, clientMap);
                }
            } else if (request instanceof RequestCreateMovie) {
                Operations.createMovie(request, dataObject);
                response = Operations.getMoviesInCategory(((RequestCreateMovie) request).getGenreId(),dataObject );
                Operations.broadcast(response, clientMap);
            } else if(request instanceof RequestStartCreateMovie){
                response = new ResponseStartMovieEdit(null, Operations.getCategories(dataObject));
                outputStream.reset();
                outputStream.writeObject(response);
                outputStream.flush();
            }
        } catch (Exception e) {
            System.out.println("error in handler: " + e);
        }

        return response;
    }
}
