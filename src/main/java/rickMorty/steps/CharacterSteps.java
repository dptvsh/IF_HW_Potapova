package rickMorty.steps;

import rickMorty.api.CharacterRickMortyApi;
import rickMorty.dto.ApiResponse;
import rickMorty.dto.Character;
import io.restassured.common.mapper.TypeRef;
import org.apache.http.HttpStatus;

public class CharacterSteps {

    private static final CharacterRickMortyApi characterApi = new CharacterRickMortyApi();

    public Character getCharacterById(long id) {
        return characterApi.getCharacterById(id)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(Character.class);
    }

    public ApiResponse<Character> getCharacterByName(String name) {
        return characterApi.getCharacterByName(name)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(new TypeRef<>() {
                });
    }
}
