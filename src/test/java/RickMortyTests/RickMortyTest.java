package RickMortyTests;

import hooks.RickMortyHooks;
import rickMorty.dto.ApiResponse;
import rickMorty.dto.Character;
import rickMorty.dto.Episode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RickMortyTest extends RickMortyHooks {

    @Test
    @DisplayName("Проверка расы и местоположения последнего персонажа последнего эпизода")
    public void compareMortyWithLastCharacterFromHisLastEpisode() {
        ApiResponse<Character> mortyResponse = characterSteps.getCharacterByName("Morty Smith");
        assertFalse(mortyResponse.results.isEmpty(), "Морти Смит не найден");
        Character morty = mortyResponse.results.getFirst();

        assertNotNull(morty.episode, "Список эпизодов null");
        assertFalse(morty.episode.isEmpty(), "У Морти нет ни одного эпизода");

        String lastEpisodeUrl = morty.episode.getLast();
        long episodeId = extractId(lastEpisodeUrl);
        Episode lastEpisode = episodeSteps.getEpisode(episodeId);

        assertNotNull(lastEpisode.characters, "Список персонажей эпизода null");
        assertFalse(lastEpisode.characters.isEmpty(), "В эпизоде нет ни одного персонажа");

        String lastCharacterUrl = lastEpisode.characters.getLast();
        long characterId = extractId(lastCharacterUrl);
        Character lastCharacter = characterSteps.getCharacterById(characterId);

        assertNotNull(morty.species, "Раса Морти null");
        assertNotNull(morty.location, "Локация Морти null");
        assertNotNull(morty.location.name, "Название локации Морти null");
        assertNotNull(lastCharacter.species, "Раса последнего персонажа null");
        assertNotNull(lastCharacter.location, "Локация последнего персонажа null");
        assertNotNull(lastCharacter.location.name, "Название локации последнего персонажа null");

        compareSpeciesAndLocation(morty, lastCharacter);
    }

    private long extractId(String url) {
        String idStr = url.replaceAll(".*/", "");
        return Long.parseLong(idStr);
    }

    private void compareSpeciesAndLocation(Character morty, Character other) {
        String mortySpecies = morty.species;
        String mortyLocation = morty.location.name;
        String otherSpecies = other.species;
        String otherLocation = other.location.name;

        System.out.println("Морти Смит: раса = " + mortySpecies + ", местоположение = " + mortyLocation);
        System.out.println("Последний персонаж: раса = " + otherSpecies + ", местоположение = " + otherLocation);

        if (mortySpecies.equals(otherSpecies) && mortyLocation.equals(otherLocation)) {
            System.out.println("Результат: персонажи совпадают по расе и местоположению");
        } else {
            System.out.println("Результат: персонажи различаются по расе или местоположению");
        }
    }
}
