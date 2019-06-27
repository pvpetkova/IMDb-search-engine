package cleancode.imdb.search;

import cleancode.imdb.model.Episode;
import cleancode.imdb.model.Movie;
import cleancode.imdb.model.Season;
import cleancode.imdb.model.TVSeries;

import java.util.List;
import java.util.Optional;

public interface Searcher {
    Optional<Movie> searchByName(String title);

    Optional<String> searchByNameAndFields(String title, List<String> fields);

    Optional<List<Episode>> searchForEpisodesBySeason(String seriesName, int seasonNumber);

    Optional<Episode> searchTVEpisode(String seriesName, int seasonNumber, int episodeNumber);

    Optional<TVSeries> searchTVSeries(String seriesName);

    Optional<Season> searchSeason(String seriesName, int seasonNumber);
}
