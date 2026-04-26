package AuthTests;

import auth.dto.RegistrationRequest;
import hooks.AuthHooks;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static auth.enums.AuthMessage.*;
import static org.junit.jupiter.api.Assertions.*;

public class AuthTest extends AuthHooks {

    @Test
    @DisplayName("Успешная регистрация")
    void registrationUser() {
        steps.register(validUser).statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("Логин с неверным username")
    void loginUserNotFoundTest() {
        steps.register(validUser);
        RegistrationRequest wrongUser = new RegistrationRequest();
        wrongUser.username = validUser.username + "_not_exists";
        wrongUser.password = validUser.password;
        String wrongUsernameBody = steps.login(wrongUser);
        assertEquals(NOT_FOUND.getMessage(), wrongUsernameBody);
    }

    @Test
    @DisplayName("Логин с неверным паролем")
    void loginWrongPasswordTest() {
        steps.register(validUser);
        RegistrationRequest wrongUser = new RegistrationRequest();
        wrongUser.username = validUser.username;
        wrongUser.password = validUser.password + "wrong";
        String wrongPasswordBody = steps.login(wrongUser);
        assertEquals(NOT_RIGHT_PASS.getMessage(), wrongPasswordBody);
    }

    @Test
    @DisplayName("Логин с верными данными")
    void loginSuccessTest() {
        steps.register(validUser);
        String tokenBody = steps.login(validUser);
        assertNotNull(tokenBody, "Токен не должен быть null");
    }

    @Test
    @DisplayName("Выход с неверным токеном")
    void logoutUnauthorizedTest() {
        steps.register(validUser);
        String fakeToken = "00000000-0000-0000-0000-000000000000";
        String logoutMessage = steps.logout(fakeToken);
        assertEquals(NOT_FOUND.getMessage(), logoutMessage);
    }

    @Test
    @DisplayName("Успешный выход")
    void logoutSuccessTest() {
        steps.register(validUser);
        String tokenBody = steps.login(validUser);
        String token = tokenBody.split("token :")[1].trim();
        String logoutMessage = steps.logout(token);
        assertEquals(SUCCESS_LOGOUT.getMessage(), logoutMessage);
    }
}
