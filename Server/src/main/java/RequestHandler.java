import model.data.request.*;
import model.data.response.ResponseShowCategories;
import model.data.response.ResponseShowMovie;
import model.data.response.ResponseStartMovieEdit;
import org.w3c.dom.Document;
import util.MarshallerUtil;
import util.XmlSender;

import java.io.OutputStream;
import java.util.Map;

class RequestHandler {
    static void handleRequest(Object request, DataObject dataObject
            , OutputStream outputStream, Map<Integer, Client> clientMap) {
        Document response;
        try {
            if (request instanceof RequestStartCategoryEdit) {
                response = Operations.lockCategory(((RequestStartCategoryEdit) request).getCategoryId(), dataObject);
                XmlSender.send(response, outputStream);
            } else if (request instanceof RequestEndCategoryEdit) {
                if (((RequestEndCategoryEdit) request).getCategoryName() == null) {
                    Operations.releaseCategory(((RequestEndCategoryEdit) request).getCategoryId(), dataObject);
                } else {
                    Operations.changeCategory(request, dataObject);
                    response = MarshallerUtil.marshallAction((new ResponseShowCategories(Operations.getCategories()))
                            ,ResponseShowCategories.class);
                    Operations.broadcast(response, clientMap);
                }
            } else if (request instanceof RequestCreateCategory) {
                Operations.createCategory(((RequestCreateCategory) request).getCategoryName(), dataObject);
                response = MarshallerUtil.marshallAction((new ResponseShowCategories(Operations.getCategories()))
                        , ResponseShowCategories.class);
                Operations.broadcast(response, clientMap);
            } else if (request instanceof RequestDeleteCategory) {
                response = Operations.deleteCategory(((RequestDeleteCategory) request).getId(), dataObject);
                Operations.broadcast(response, clientMap);
            } else if (request instanceof RequestStartMovieEdit) {
                response = Operations.lockMovie(((RequestStartMovieEdit) request).getMovieId(), dataObject);
                XmlSender.send(response, outputStream);
            } else if (request instanceof RequestShowCategories) {
                response = MarshallerUtil.marshallAction((new ResponseShowCategories(Operations.getCategories()))
                        , ResponseShowCategories.class);
                XmlSender.send(response, outputStream);
            } else if (request instanceof RequestShowMovie) {
                response = MarshallerUtil.marshallAction((new ResponseShowMovie(Operations.
                        getMovie(((RequestShowMovie) request).getMovieId()))), ResponseShowMovie.class);
                XmlSender.send(response, outputStream);
            } else if (request instanceof RequestShowMovieList) {
                response = Operations.getMoviesInCategory(((RequestShowMovieList) request).getCategoryId());
                XmlSender.send(response, outputStream);
            } else if (request instanceof RequestEndMovieEdit) {
                if (((RequestEndMovieEdit) request).getName() == null) {
                    Operations.releaseMovie(((RequestEndMovieEdit) request).getId(), dataObject);
                } else {
                    response = Operations.changeMovie(request, dataObject);
                    Operations.broadcast(response, clientMap);
                }
            } else if (request instanceof RequestCreateMovie) {
                Operations.createMovie(request, dataObject);
                response = Operations.getMoviesInCategory(((RequestCreateMovie) request).getGenreId());
                Operations.broadcast(response, clientMap);
            } else if(request instanceof RequestStartCreateMovie){
                response = MarshallerUtil.marshallAction((new ResponseStartMovieEdit(null, Operations.getCategories()))
                        , ResponseStartMovieEdit.class);
                XmlSender.send(response, outputStream);
            } else if(request instanceof RequestDeleteMovie){
                response = Operations.deleteMovie(request, dataObject);
                Operations.broadcast(response, clientMap);
            }
        } catch (Exception e) {
            System.out.println("error in handler: " + e);
        }

        //return response;
    }
}
