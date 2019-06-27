package cleancode.imdb.search;

import cleancode.imdb.model.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePersist {
    public FilePersist(Path source) {
        localStorageSource = source;
    }

    public FilePersist(String source) {
        localStorageSource = Paths.get(cleanupFileName(source));
    }

    public void saveInLocalStorage(Movie movie) {
        try (BufferedWriter writer = Files.newBufferedWriter(resolve(movie.getTitle()))) {
            new ObjectMapper().writeValue(writer, movie);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String cleanupFileName(String fileName) {
        return fileName.replace("\\W+", "") + ".json";
    }

    public Path resolve(String name) {
        return localStorageSource.resolve(cleanupFileName(name));
    }

    private final Path localStorageSource;
}
