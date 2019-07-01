package cleancode.imdb.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class LocalFilesSearcher implements Searcher {
    private FilePersist filePersist;

    public LocalFilesSearcher() {
        this.filePersist = new FilePersist();
    }

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
        if (Files.exists(file)) {
            try (BufferedReader fileReader = Files.newBufferedReader(file)) {
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
