package AuthTests;

import dto.Auth.RegistrationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.Auth.AuthSteps;
import utils.JsonReader;

import static org.junit.jupiter.api.Assertions.*;

public class AuthTest {
    private AuthSteps steps;
    private RegistrationRequest validUser;

    @BeforeEach
    public void setUp() {
        steps = new AuthSteps();
        validUser = JsonReader.readRegistrationRequest("src/test/resources/user.json");
    }

    @Test
    @DisplayName("тест на Регистрацию + Авторизацию + Выход из учетной записи")
    public void fullAuthFlowTest() {
        steps.registerUser(validUser);

        String wrongUsernameBody = steps.loginUserNotFound(validUser);
        assertEquals("not found", wrongUsernameBody);

        String wrongPasswordBody = steps.loginWrongPassword(validUser);
        assertEquals("not right pass", wrongPasswordBody);

        String tokenBody = steps.loginSuccess(validUser);
        assertNotNull(tokenBody, "Токен не должен быть null");
        String token = tokenBody.split("token :")[1].trim();

        String fakeLogoutMessage = steps.logoutUnauthorized("00000000-0000-0000-0000-000000000000");
        assertEquals("not found", fakeLogoutMessage);

        String successLogoutMessage = steps.logoutSuccess(token);
        assertEquals("success logout", successLogoutMessage);
    }
}
