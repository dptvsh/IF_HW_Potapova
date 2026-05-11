package cucumber.steps.auth;

import auth.dto.RegistrationRequest;
import auth.enums.AuthMessage;
import config.ConfigReader;
import io.cucumber.java.ParameterType;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;
import org.apache.http.HttpStatus;
import utils.JsonReader;

import static org.junit.jupiter.api.Assertions.*;

public class AuthSteps {
    private final AuthContext context;

    public AuthSteps(AuthContext context) {
        this.context = context;
    }

    @Дано("данные из файла конфигурации загружены")
    public void configAreLoaded() {
        context.validUser = JsonReader.readRegistrationRequest(ConfigReader.get("user"));
        assertNotNull(context.validUser, "Данные из конфига не были загружены");
    }

    @И("пользователь зарегистрирован")
    public void userAlreadyRegistered() {
        RegistrationRequest user = context.validUser;
        context.lastResponse = context.authSteps.register(user);
        context.lastResponse.statusCode(HttpStatus.SC_OK);
    }

    @И("пользователь вошел в свой аккаунт")
    public void userAlreadyLogin() {
        RegistrationRequest user = context.validUser;
        context.lastResponse = context.authSteps.login(user);
        context.lastResponse.statusCode(HttpStatus.SC_OK);
        context.tokenBody = context.lastResponse.extract().body().asString();
        assertNotNull(context.tokenBody, "Токен не должен быть null");
        assertTrue(context.tokenBody.startsWith("token : "), "Некорректный формат токена");
        context.token = context.tokenBody.split("token : ")[1].trim();
    }

    @Когда("пользователь регистрируется")
    public void registrationUser() {
        RegistrationRequest user = context.validUser;
        context.lastResponse = context.authSteps.register(user);
    }

    @Когда("пользователь входит с неверным username")
    public void loginUserWrongUsername() {
        context.lastResponse = context.authSteps.login(createWrongUser("_not_exists", ""));
    }

    @Когда("пользователь входит с неверным паролем")
    public void loginUserWrongPassword() {
        context.lastResponse = context.authSteps.login(createWrongUser("", "wrong"));
    }

    @Когда("пользователь входит с верными данными")
    public void successLogin() {
        RegistrationRequest user = context.validUser;
        context.lastResponse = context.authSteps.login(user);
        context.tokenBody = context.lastResponse.extract().body().asString();
    }

    @Тогда("пользователь получает ответ с токеном \\(статус код: {int}\\)")
    @Step("пользователь получает ответ с токеном (статус код: {expectedStatus})")
    public void getValidToken(int expectedStatus) {
        context.lastResponse.statusCode(expectedStatus);
        assertNotNull(context.tokenBody, "Токен не должен быть null");
        assertTrue(context.tokenBody.startsWith("token : "), "Некорректный формат токена");
    }

    @Когда("пользователь пытается выйти с неверным токеном")
    public void logoutUnauthorized() {
        String fakeToken = "00000000-0000-0000-0000-000000000000";
        context.lastResponse = context.authSteps.logout(fakeToken);
    }

    @Когда("пользователь выходит с верным токеном")
    public void successLogout() {
        context.lastResponse = context.authSteps.logout(context.token);
    }

    @Step("пользователь получает сообщение {expectedMessage} (статус код: {expectedStatus})")
    @Тогда("пользователь получает сообщение {authMessage} \\(статус код: {int}\\)")
    public void checkMessageAndCode(AuthMessage expectedMessage, int expectedStatus) {
        context.lastResponse.statusCode(expectedStatus);
        assertEquals(expectedMessage.getMessage(), context.lastResponse.extract().body().asString());
    }

    @ParameterType(".*")
    public AuthMessage authMessage(String name) {
        try {
            return AuthMessage.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Некорректное имя сообщения: " + name);
        }
    }

    private RegistrationRequest createWrongUser(String usernameModifier, String passwordModifier) {
        RegistrationRequest wrong = new RegistrationRequest();
        wrong.username = context.validUser.username + usernameModifier;
        wrong.password = context.validUser.password + passwordModifier;
        return wrong;
    }
}
