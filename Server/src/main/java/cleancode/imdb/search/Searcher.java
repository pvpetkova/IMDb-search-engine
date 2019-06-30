package cleancode.imdb.search;

import cleancode.imdb.model.Movie;

import java.util.List;
import java.util.Optional;

public interface Searcher {
//    Optional<Movie> searchByName(String title);

    Optional<String> searchByNameAndFields(String title, List<String> fields);

    Optional<String> searchTVSeries(String seriesName);
}
