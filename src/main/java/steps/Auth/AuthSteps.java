package steps.Auth;

import api.Auth.AuthApi;
import dto.Auth.RegistrationRequest;
import org.apache.http.HttpStatus;

public class AuthSteps {
    private final AuthApi authApi = new AuthApi();

    public void registerUser(RegistrationRequest request) {
        authApi.register(request)
                .statusCode(HttpStatus.SC_OK);
    }

    public String loginUserNotFound(RegistrationRequest originalRequest) {
        RegistrationRequest wrongUser = new RegistrationRequest();
        wrongUser.username = originalRequest.username + "_not_exists";
        wrongUser.password = originalRequest.password;

        return authApi.login(wrongUser)
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .extract()
                .body()
                .asString();
    }

    public String loginWrongPassword(RegistrationRequest originalRequest) {
        RegistrationRequest wrongPass = new RegistrationRequest();
        wrongPass.username = originalRequest.username;
        wrongPass.password = originalRequest.password + "wrong";

        return authApi.login(wrongPass)
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .extract()
                .body()
                .asString();
    }

    public String loginSuccess(RegistrationRequest request) {
        return authApi.login(request)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .asString();
    }

    public String logoutUnauthorized(String fakeToken) {
        return authApi.logout(fakeToken)
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .extract()
                .body()
                .asString();
    }

    public String logoutSuccess(String token) {
        return authApi.logout(token)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .asString();
    }
}
