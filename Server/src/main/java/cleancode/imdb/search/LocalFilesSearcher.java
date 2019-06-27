package cleancode.imdb.search;

import cleancode.imdb.model.Episode;
import cleancode.imdb.model.Movie;
import cleancode.imdb.model.Season;
import cleancode.imdb.model.TVSeries;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class LocalFilesSearcher implements Searcher {
    //TODO: logic for searching in local storage
    private FilePersist filePersist;

    public LocalFilesSearcher(Path source) {
        this.filePersist = new FilePersist(source);
    }

    public static Movie toMovieObject(File file) {
        try {
            return new ObjectMapper().readValue(file, Movie.class);
        } catch (IOException a) {
            a.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Movie> searchByName(String title) {
        Path file = filePersist.resolve(filePersist.cleanupFileName(title));
        if (Files.exists(file)) {
            return Optional.ofNullable(toMovieObject(file.toFile()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> searchByNameAndFields(String title, List<String> fields) {
        Path file = filePersist.resolve(filePersist.cleanupFileName(title));
        if (Files.exists(file)) {
            try (BufferedReader fileReader = Files.newBufferedReader(file)) {
                return Optional.of(JsonFileFilter.filterByFields(fileReader, fields));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Episode>> searchForEpisodesBySeason(String seriesName, int seasonNumber) {
        return Optional.empty();
    }

    @Override
    public Optional<Episode> searchTVEpisode(String seriesName, int seasonNumber, int episodeNumber) {
        return Optional.empty();
    }

    @Override
    public Optional<TVSeries> searchTVSeries(String seriesName) {
        return Optional.empty();
    }

    @Override
    public Optional<Season> searchSeason(String seriesName, int seasonNumber) {
        return Optional.empty();
    }
}
