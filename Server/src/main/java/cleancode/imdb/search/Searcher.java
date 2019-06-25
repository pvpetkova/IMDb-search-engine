package cleancode.imdb.search;

import cleancode.imdb.model.Episode;
import cleancode.imdb.model.Movie;
import cleancode.imdb.model.Season;

import java.util.List;
import java.util.Optional;

public interface Searcher {
    Optional<Movie> searchByName(String title);
    Optional<String> searchByNameAndFields(String title, List<String> fields);
    Optional<List<Episode>> searchForEpisodesBySeason(String seriesName, int seasonNumber);
    Optional<Season> searchTVSeason(String seriesName);
}
