package cleancode.imdb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
