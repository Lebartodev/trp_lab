package main.java.model;

import java.io.File;

public enum SchemaPath {

    CategorySchema("Shared/src/main/java/model/category_schema.xsd"),
    MovieSchema("Shared/src/main/java/model/movie_schema.xsd"),
    RequestCreateCategorySchema("Shared/src/main/java/model/data/request/schema/request_create_category_schema.xsd"),
    RequestDeleteCategorySchema("Shared/src/main/java/model/data/request/schema/request_delete_category.xsd");


    private final File file;

    SchemaPath(String fileName) {
        this.file = new File(fileName);
    }

    public File getFile() {
        return file;
    }
}
