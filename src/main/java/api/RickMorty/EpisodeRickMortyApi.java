package api.RickMorty;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class EpisodeRickMortyApi extends BaseRickMortyApi {
    private static final String EPISODE_URN = "/episode";

    public ValidatableResponse getEpisode(long id) {
        return given()
                .when()
                .get(EPISODE_URN + "/" + id)
                .then();
    }
}
