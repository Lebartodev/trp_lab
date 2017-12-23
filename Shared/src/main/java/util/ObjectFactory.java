package util;

import model.data.request.RequestCreateCategory;
import model.data.request.RequestShowCategories;
import model.data.response.*;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    // request
    public RequestShowCategories createRequestShowCategories() {
        return new RequestShowCategories();
    }
    public RequestCreateCategory createRequestCreateCategory(){
        return new RequestCreateCategory();
    }


    //response
    public ResponseException createResponseException(){
        return new ResponseException();
    }
    public ResponseMovieEditedData createResponseMovieEditedData(){
        return new ResponseMovieEditedData();
    }
    public ResponseShowCategories createResponseShowCategories(){
        return new ResponseShowCategories();
    }
    public ResponseShowMovie createResponseShowMovie(){
        return new ResponseShowMovie();
    }
    public ResponseShowMovieList createResponseShowMovieList(){
        return new ResponseShowMovieList();
    }
    public ResponseStartCategoryEdit createResponseStartCategoryEdit(){
        return new ResponseStartCategoryEdit();
    }
    public ResponseStartMovieEdit createResponseStartMovieEdit(){
        return new ResponseStartMovieEdit();
    }
}
