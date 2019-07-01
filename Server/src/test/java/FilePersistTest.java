import cleancode.imdb.model.Movie;
import cleancode.imdb.model.Rating;
import cleancode.imdb.search.FilePersist;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FilePersistTest {
    private FilePersist persister;
    Movie movie;

    @Before
    public void before() {
        persister = new FilePersist();
        List<Rating> ratings = new ArrayList<>();
        ratings.add(new Rating("Internet Movie Database", "8.8/10"));
        ratings.add(new Rating("Rotten Tomatoes", "86%"));
        ratings.add(new Rating("Metacritic", "74/100"));
        movie = new Movie("Inception", "2010", "PG-13", "16 Jul 2010", "148 min",
                "Action, Adventure, Sci-Fi", "Christopher Nolan", "Christopher Nolan",
                "Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page, Tom Hardy",
                "A thief, who steals corporate secrets through use of dream-sharing technology, " +
                        "is given the inverse task of planting an idea into the mind of a CEO.",
                "English, Japanese, French", "USA, UK", "Won 4 Oscars. Another 150 wins & 203 nominations.",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SX300.jpg",
                ratings, "74", "8.8", "1,592,306", "tt1375666",
                "movie", "07 Dec 2010", "$292,568,851", "Warner Bros. Pictures",
                "http://inceptionmovie.warnerbros.com/", "True");
    }

    @Test
    public void testCleanup() {
        Assert.assertEquals("Inception.json", persister.cleanupFileName("Inception"));
    }

    @Test
    public void testResolution() {
        String path = "C:\\Users\\Home\\Documents\\fmi_cc\\omdb_data\\Inception.json";
        Assert.assertEquals(path, persister.resolve("Inception").toString());
    }

    @Test
    public void testFileIsCreated() {
        persister.saveInLocalStorage(movie);
        Assert.assertTrue("File wasn't created",Files.exists(persister.resolve(movie.getTitle())));
    }
}
