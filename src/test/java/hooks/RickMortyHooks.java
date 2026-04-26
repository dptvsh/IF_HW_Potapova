package hooks;

import org.junit.jupiter.api.BeforeAll;
import rickMorty.steps.CharacterSteps;
import rickMorty.steps.EpisodeSteps;

public class RickMortyHooks {
    protected static CharacterSteps characterSteps;
    protected static EpisodeSteps episodeSteps;

    @BeforeAll
    static void setUp() {
        characterSteps = new CharacterSteps();
        episodeSteps = new EpisodeSteps();
    }
}
