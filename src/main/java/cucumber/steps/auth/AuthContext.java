package cucumber.steps.auth;

import auth.dto.RegistrationRequest;
import auth.steps.AuthAPISteps;
import io.restassured.response.ValidatableResponse;

public class AuthContext {
    public final AuthAPISteps authSteps;
    public RegistrationRequest validUser;
    public ValidatableResponse lastResponse;
    public String token;
    public String tokenBody;


    public AuthContext(AuthAPISteps authSteps) {
        this.authSteps = authSteps;
    }
}
