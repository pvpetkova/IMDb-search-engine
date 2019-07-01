package cleancode.imdb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TVSeries extends Listing {
    @JsonProperty(value = "totalSeasons")
    private String totalSeasons;
    @JsonProperty(value = "Response")
    private String response;

    @Override
    public String toString() {
        return "TVSeries{" +
                super.toString() +
                ", totalSeasons='" + totalSeasons + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}
