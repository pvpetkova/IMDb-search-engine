package cleancode.imdb.search;

import cleancode.imdb.config.ConfigHandler;
import cleancode.imdb.model.Movie;
import cleancode.imdb.model.TVSeries;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RESTSearcher implements Searcher {
    private FilePersist filePersist;
    private WebTarget target;

    public RESTSearcher() {
        this.filePersist = new FilePersist();
        String API_URL = ConfigHandler.getInstance().getApiURL();
        target = ClientBuilder.newBuilder()
                .register(JacksonFeature.class)
                .build()
                .target(API_URL);
    }

    @Override
    public Optional<String> searchByNameAndFields(String title, List<String> fields) {
        Movie movie = getMovieFromApi(title);
        if (Objects.isNull(movie)) {
            return Optional.empty();
        }
        filePersist.saveInLocalStorage(movie);
        try (BufferedReader bufferedReader = Files.newBufferedReader(filePersist.resolve(title))) {
            return Optional.of(JsonFileFilter.filterByFields(bufferedReader, fields));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> searchTVSeries(String seriesName) {
        TVSeries series = getTVSeriesFromApi(seriesName);
        if (Objects.isNull(series)) {
            return Optional.empty();
        }
        filePersist.saveInLocalStorage(series);
        return Optional.of(series.toString());
    }

    private Movie getMovieFromApi(String title) {
        return target.queryParam("t", title)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(Movie.class);
    }

    private TVSeries getTVSeriesFromApi(String seriesName) {
        return target.queryParam("t", seriesName)
                .queryParam("type", "series")
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(TVSeries.class);
    }
}
