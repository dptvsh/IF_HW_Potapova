package cucumber.steps.rickMorty;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RickMortySteps {
    private final RickMortyContext context;

    public RickMortySteps(RickMortyContext context) {
        this.context = context;
    }

    @Когда("^в API найдены все персонажи с именем '(.*)'")
    public void findCharacterList(String characterName) {
        context.mortyResponse = context.characterSteps.getCharacterByName(characterName);
        Assertions.assertFalse(context.mortyResponse.results.isEmpty(), characterName + " не найден");
    }

    @Когда("^первый персонаж '(.*)' найден в API")
    public void findCharacter(String characterName) {
        context.morty = context.mortyResponse.results.getFirst();
        assertNotNull(context.morty.episode, "Список эпизодов null");
        assertFalse(context.morty.episode.isEmpty(), "У " + characterName + " нет ни одного эпизода");
    }

    @И("^найден последний эпизод, где появляется персонаж '(.*)'")
    public void getLastEpisode(String characterName) {
        String lastEpisodeUrl = context.morty.episode.getLast();
        long episodeId = extractId(lastEpisodeUrl);
        context.lastEpisode = context.episodeSteps.getEpisode(episodeId);

        assertNotNull(context.lastEpisode.characters, "Список персонажей эпизода null");
        assertFalse(context.lastEpisode.characters.isEmpty(), "В эпизоде нет ни одного персонажа");
    }

    @И("в последнем эпизоде найден последний персонаж")
    public void getLasCharacter() {
        String lastCharacterUrl = context.lastEpisode.characters.getLast();
        long characterId = extractId(lastCharacterUrl);
        context.lastCharacter = context.characterSteps.getCharacterById(characterId);

        assertNotNull(context.morty.species, "Раса Морти null");
        assertNotNull(context.morty.location, "Локация Морти null");
        assertNotNull(context.morty.location.name, "Название локации Морти null");
        assertNotNull(context.lastCharacter.species, "Раса последнего персонажа null");
        assertNotNull(context.lastCharacter.location, "Локация последнего персонажа null");
        assertNotNull(context.lastCharacter.location.name, "Название локации последнего персонажа null");
    }

    @Тогда("^последний персонаж сравнивается с первым персонажем")
    public void compareCharacters() {
        String mortySpecies = context.morty.species;
        String mortyLocation = context.morty.location.name;
        String lastCharSpecies = context.lastCharacter.species;
        String lastCharLocation = context.lastCharacter.location.name;

        System.out.println("Морти Смит: раса = " + mortySpecies + ", местоположение = " + mortyLocation);
        System.out.println("Последний персонаж: раса = " + lastCharSpecies + ", местоположение = " + lastCharLocation);

        if (mortySpecies.equals(lastCharSpecies) && mortyLocation.equals(lastCharLocation)) {
            System.out.println("Результат: персонажи совпадают по расе и местоположению");
        } else {
            System.out.println("Результат: персонажи различаются по расе или местоположению");
        }
    }

    private long extractId(String url) {
        String idStr = url.replaceAll(".*/", "");
        return Long.parseLong(idStr);
    }
}
