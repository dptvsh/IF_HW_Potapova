package cucumber.steps.rickMorty;

import rickMorty.dto.ApiResponse;
import rickMorty.dto.Character;
import rickMorty.dto.Episode;
import rickMorty.steps.CharacterSteps;
import rickMorty.steps.EpisodeSteps;

public class RickMortyContext {
    public final EpisodeSteps episodeSteps;
    public final CharacterSteps characterSteps;

    ApiResponse<Character> mortyResponse;
    public Character morty;
    public Episode lastEpisode;
    public Character lastCharacter;

    public RickMortyContext(CharacterSteps characterSteps, EpisodeSteps episodeSteps) {
        this.characterSteps = characterSteps;
        this.episodeSteps = episodeSteps;
    }
}
