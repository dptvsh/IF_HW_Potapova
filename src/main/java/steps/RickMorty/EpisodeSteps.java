package steps.RickMorty;

import api.RickMorty.EpisodeRickMortyApi;
import dto.RickMorty.Episode;
import org.apache.http.HttpStatus;

public class EpisodeSteps {

    private static final EpisodeRickMortyApi episodeApi = new EpisodeRickMortyApi();

    public Episode getEpisode(long id) {
        return episodeApi.getEpisode(id)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(Episode.class);
    }
}
