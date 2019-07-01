package cleancode.imdb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie extends Listing {
    @JsonProperty(value = "DVD")
    private String dvd;
    @JsonProperty(value = "BoxOffice")
    private String boxOffice;
    @JsonProperty(value = "Production")
    private String production;
    @JsonProperty(value = "Website")
    private String website;
    @JsonProperty(value = "Response")
    private String response;

    public Movie(String title, String year, String rated, String released, String runtime, String genre, String director, String writer, String actors, String plot, String language, String country, String awards, String poster, List<Rating> ratings, String metascore, String imdbRating, String imdbVotes, String imdbId, String type, String dvd, String boxOffice, String production, String website, String response) {
        super(title, year, rated, released, runtime, genre, director, writer, actors, plot, language, country, awards, poster, ratings, metascore, imdbRating, imdbVotes, imdbId, type);
        this.dvd = dvd;
        this.boxOffice = boxOffice;
        this.production = production;
        this.website = website;
        this.response = response;
    }

    @Override
    public String toString() {
        return "Movie{" +
                super.toString() +
                ", dvd='" + dvd + '\'' +
                ", boxOffice='" + boxOffice + '\'' +
                ", production='" + production + '\'' +
                ", website='" + website + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}
