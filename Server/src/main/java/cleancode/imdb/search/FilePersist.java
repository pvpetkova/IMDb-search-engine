package cleancode.imdb.search;

import cleancode.imdb.config.ConfigHandler;
import cleancode.imdb.model.Listing;
import cleancode.imdb.model.Movie;
import cleancode.imdb.model.TVSeries;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePersist {
    public FilePersist() {
        localStorageSource = ConfigHandler.getInstance().getSourceDir();
    }

    public void saveInLocalStorage(Listing listing) {
        try (BufferedWriter writer = Files.newBufferedWriter(resolve(listing.getTitle()))) {
            new ObjectMapper().writeValue(writer, listing);
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

    private Path localStorageSource;
}
