package cleancode.imdb.search;

import cleancode.imdb.model.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class LocalFilesSearcher implements Searcher {
    private FilePersist filePersist;

    public LocalFilesSearcher(Path source) {
        this.filePersist = new FilePersist(source);
    }

//    private static Movie toMovieObject(File file) {
//        try {
//            return new ObjectMapper().readValue(file, Movie.class);
//        } catch (IOException a) {
//            a.printStackTrace();
//            return null;
//        }
//    }
//
//    @Override
//    public Optional<Movie> searchByName(String title) {
//        Path file = filePersist.resolve(filePersist.cleanupFileName(title));
//        if (Files.exists(file)) {
//            return Optional.ofNullable(toMovieObject(file.toFile()));
//        }
//        return Optional.empty();
//    }

    @Override
    public Optional<String> searchByNameAndFields(String title, List<String> fields) {
        Path file = filePersist.resolve(filePersist.cleanupFileName(title));
        if (Files.exists(file)) {
            try (BufferedReader fileReader = Files.newBufferedReader(file)) {
                return Optional.of(JsonFileFilter.filterByFields(fileReader, fields));
            } catch (IOException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> searchTVSeries(String seriesName) {
        Path file = filePersist.resolve(filePersist.cleanupFileName(seriesName));
        if(Files.exists(file)) {
            try(BufferedReader fileReader = Files.newBufferedReader(file)) {
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = fileReader.readLine()) != null) {
                    builder.append(line);
                }
                return Optional.of(builder.toString());
            } catch (IOException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }
}
