package util;

import model.data.request.*;
import model.data.response.*;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    // request
    public RequestShowCategories createRequestShowCategories() {
        return new RequestShowCategories();
    }

    public RequestCreateCategory createRequestCreateCategory() {
        return new RequestCreateCategory();
    }

    public RequestCreateMovie createRequestCreateMovie() {
        return new RequestCreateMovie();
    }

    public RequestDeleteCategory createRequestDeleteCategory() {
        return new RequestDeleteCategory();
    }

    public RequestDeleteMovie createRequestDeleteMovie() {
        return new RequestDeleteMovie();
    }

    public RequestEndCategoryEdit createRequestEndCategoryEdit() {
        return new RequestEndCategoryEdit();
    }

    public RequestEndMovieEdit createRequestEndMovieEdit() {
        return new RequestEndMovieEdit();
    }

    public RequestExit createRequestExit() {
        return new RequestExit();
    }

    public RequestShowMovie createRequestShowMovie() {
        return new RequestShowMovie();
    }

    public RequestShowMovieList createRequestShowMovieList() {
        return new RequestShowMovieList();
    }

    public RequestStartCategoryEdit createRequestStartCategoryEdit() {
        return new RequestStartCategoryEdit();
    }

    public RequestStartCreateMovie createRequestStartCreateMovie() {
        return new RequestStartCreateMovie();
    }

    public RequestStartMovieEdit createRequestStartMovieEdit() {
        return new RequestStartMovieEdit();
    }


    //response
    public ResponseException createResponseException() {
        return new ResponseException();
    }

    public ResponseMovieEditedData createResponseMovieEditedData() {
        return new ResponseMovieEditedData();
    }

    public ResponseShowCategories createResponseShowCategories() {
        return new ResponseShowCategories();
    }

    public ResponseShowMovie createResponseShowMovie() {
        return new ResponseShowMovie();
    }

    public ResponseShowMovieList createResponseShowMovieList() {
        return new ResponseShowMovieList();
    }

    public ResponseStartCategoryEdit createResponseStartCategoryEdit() {
        return new ResponseStartCategoryEdit();
    }

    public ResponseStartMovieEdit createResponseStartMovieEdit() {
        return new ResponseStartMovieEdit();
    }
}
