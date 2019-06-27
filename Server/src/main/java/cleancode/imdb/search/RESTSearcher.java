package cleancode.imdb.search;

import cleancode.imdb.model.Episode;
import cleancode.imdb.model.Movie;
import cleancode.imdb.model.Season;
import cleancode.imdb.model.TVSeries;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RESTSearcher implements Searcher {
    private String API_URL;
    private FilePersist filePersist;

    public RESTSearcher(Path source) {
        this.filePersist = new FilePersist(source);
    }

    private WebTarget target = newTarget();

    private WebTarget newTarget() {
        return ClientBuilder.newBuilder()
                .register(JacksonFeature.class)
                .build()
                .target(API_URL);
    }

    public void setAPI_URL(String url) {
        API_URL = url;
        target = newTarget();
    }

    @Override
    public Optional<Movie> searchByName(String title) {
        Movie movie = getMovieFromApi(title);
        filePersist.saveInLocalStorage(movie);
        return Optional.of(movie);
    }

    @Override
    public Optional<String> searchByNameAndFields(String title, List<String> fields) {
        Movie movie = getMovieFromApi(title);
        if (Objects.isNull(movie))
            return Optional.empty();
        filePersist.saveInLocalStorage(movie);
        try (BufferedReader bufferedReader = Files.newBufferedReader(filePersist.resolve(title))) {
            return Optional.of(JsonFileFilter.filterByFields(bufferedReader, fields));
        } catch (IOException e) {
            e.printStackTrace();
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

    private Movie getMovieFromApi(String title) {
        return target.queryParam("t", title)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(Movie.class);
    }

    //TODO: logic for using the OMDb API
}
