package api.Auth;

import dto.Auth.RegistrationRequest;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class AuthApi extends BaseAuthApi {

    private static final String REGISTER = "/register";
    private static final String LOGIN = "/login";
    private static final String LOGOUT = "/logout";

    public ValidatableResponse register(RegistrationRequest request) {
        return given()
                .body(request)
                .when()
                .post(REGISTER)
                .then();
    }

    public ValidatableResponse login(RegistrationRequest request) {
        return given()
                .body(request)
                .when()
                .post(LOGIN)
                .then();
    }

    public ValidatableResponse logout(String token) {
        return given()
                .header("Authorization", token)
                .when()
                .get(LOGOUT)
                .then();
    }
}
