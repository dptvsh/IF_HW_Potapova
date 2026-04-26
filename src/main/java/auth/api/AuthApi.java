package auth.api;

import auth.dto.RegistrationRequest;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class AuthApi extends BaseAuthApi {

    public ValidatableResponse postUser(RegistrationRequest request, String urn) {
        return given()
                .body(request)
                .when()
                .post(urn)
                .then();
    }

    public ValidatableResponse logout(String token, String urn) {
        return given()
                .header("Authorization", token)
                .when()
                .get(urn)
                .then();
    }
}
