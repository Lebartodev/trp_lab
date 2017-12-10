package main.java.model;

import java.io.File;

public enum SchemaPath {

    CategorySchema("Shared/src/main/java/model/category_schema.xsd"),
    MovieSchema("Shared/src/main/java/model/movie_schema.xsd");



    private final File file;

    SchemaPath(String fileName) {
        this.file = new File(fileName);
    }

    public File getFile() {
        return file;
    }
}
