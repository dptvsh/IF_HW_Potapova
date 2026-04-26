package hooks;

import config.ConfigReader;
import auth.dto.RegistrationRequest;
import org.junit.jupiter.api.BeforeEach;
import auth.steps.AuthSteps;
import utils.JsonReader;

public class AuthHooks {

    protected AuthSteps steps;
    protected RegistrationRequest validUser;

    @BeforeEach
    public void setUp() {
        steps = new AuthSteps();
        validUser = JsonReader.readRegistrationRequest(ConfigReader.get("user"));
    }
}
