package auth.steps;

import auth.api.AuthApi;
import auth.dto.RegistrationRequest;
import io.restassured.response.ValidatableResponse;

import static auth.enums.AuthUrn.*;

public class AuthAPISteps {
    private final AuthApi authApi = new AuthApi();

    public ValidatableResponse register(RegistrationRequest request) {
        return authApi.postUser(request, REGISTER.getUrn());
    }

    public ValidatableResponse login(RegistrationRequest request) {
        return authApi.postUser(request, LOGIN.getUrn());
    }

    public ValidatableResponse logout(String token) {
        return authApi.logout(token, LOGOUT.getUrn());
    }
}
