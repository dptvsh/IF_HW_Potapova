package rickMorty.api;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CharacterRickMortyApi extends BaseRickMortyApi {

    private static final String CHARACTER_URN = "/character";

    public ValidatableResponse getCharacterById(long id) {
        return given()
                .when()
                .get(CHARACTER_URN + "/" + id)
                .then();
    }

    public ValidatableResponse getCharacterByName(String name) {
        return given()
                .when()
                .queryParam("name", name)
                .get(CHARACTER_URN)
                .then();
    }
}
