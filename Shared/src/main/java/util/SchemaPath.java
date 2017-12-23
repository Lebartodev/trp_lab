package util;

import java.io.File;

public enum SchemaPath {

    CategorySchema("Shared/src/main/java/model/category_schema.xsd"),
    MovieSchema("Shared/src/main/java/model/movie_schema.xsd"),

    //requests
    RequestCreateCategorySchema("Shared/src/main/java/model/data/request/schema/request_create_category_schema.xsd"),
    RequestDeleteCategorySchema("Shared/src/main/java/model/data/request/schema/request_delete_category.xsd"),
    RequestDeleteMovieSchema("Shared/src/main/java/model/data/request/schema/request_delete_movie.xsd"),
    RequestCreateMovieSchema("Shared/src/main/java/model/data/request/schema/request_create_movie.xsd"),
    RequestEndCategoryEditSchema("Shared/src/main/java/model/data/request/schema/request_end_category_edit.xsd"),
    RequestEndMovieEditSchema("Shared/src/main/java/model/data/request/schema/request_end_movie_edit.xsd"),
    RequestExitSchema("Shared/src/main/java/model/data/request/schema/request_exit.xsd"),
    RequestShowCategoriesSchema("Shared/src/main/java/model/data/request/schema/request_show_categories.xsd"),
    RequestShowMovieSchema("Shared/src/main/java/model/data/request/schema/request_show_movie.xsd"),
    RequestShowMovieListSchema("Shared/src/main/java/model/data/request/schema/request_show_movie_list.xsd"),
    RequestStartCategoryEditSchema("Shared/src/main/java/model/data/request/schema/request_start_category_edit.xsd"),
    RequestStartCreateMovieSchema("Shared/src/main/java/model/data/request/schema/request_start_create_movie.xsd"),
    RequestStartMovieEditSchema("Shared/src/main/java/model/data/request/schema/request_start_movie_edit.xsd"),

    //responses
    ResponseExceptionSchema("Shared/src/main/java/model/data/response/schema/response_exception.xsd"),
    ResponseMovieEditedDataSchema("Shared/src/main/java/model/data/response/schema/response_movie_edited_data.xsd"),
    ResponseShowCategoriesSchema("Shared/src/main/java/model/data/response/schema/response_show_categories.xsd"),
    ResponseShowMovieSchema("Shared/src/main/java/model/data/response/schema/response_show_movie.xsd"),
    ResponseShowMovieListSchema("Shared/src/main/java/model/data/response/schema/response_show_movie_list.xsd"),
    ResponseStartCategoryEditSchema("Shared/src/main/java/model/data/response/schema/response_start_category_edit.xsd"),
    ResponseStartMovieEditSchema("Shared/src/main/java/model/data/response/schema/response_start_movie_edit.xsd")
    ;


    private final File file;

    SchemaPath(String fileName) {
        this.file = new File(fileName);
    }

    public File getFile() {
        return file;
    }
}
