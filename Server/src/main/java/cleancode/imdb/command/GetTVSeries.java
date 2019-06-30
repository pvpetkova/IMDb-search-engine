package cleancode.imdb.command;

import cleancode.imdb.search.LocalFilesSearcher;
import cleancode.imdb.search.RESTSearcher;

import java.util.List;

public class GetTVSeries extends AbstractCommand {
    private LocalFilesSearcher localFilesSearch;
    private RESTSearcher restClientSearch;

    public GetTVSeries(LocalFilesSearcher localFilesSearch, RESTSearcher restClientSearch) {
        this.localFilesSearch = localFilesSearch;
        this.restClientSearch = restClientSearch;
    }

    @Override
    public String execute(List<String> args) {
        String seriesName = args.get(1);
        if (args.size() < 2 || seriesName.startsWith("-")) {
            return helpMessage();
        }

        return localFilesSearch.searchTVSeries(seriesName)
                .orElseGet(() -> String.valueOf(restClientSearch.searchTVSeries(seriesName)));
    }

    private String helpMessage() {
        return String.format("Usage:\n%s", "get-tv-series <name>");
    }
}
