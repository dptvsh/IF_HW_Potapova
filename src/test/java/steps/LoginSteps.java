package steps;

import EduJiraIFPages.LoginPage;
import config.ConfigReader;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;

public class LoginSteps {

    private final ScenarioContext context;
    private final LoginPage loginPage;

    public LoginSteps(ScenarioContext context) {
        this.context = context;
        this.loginPage = new LoginPage();
    }

    @Дано("открыта главная страница с формой авторизации")
    public void loginPageShouldBeOpened() {
        loginPage.isOpened();
    }

    @Когда("пользователь вводит свои username и password")
    public void loginWithDefaultCredentials() {
        context.dashboardPage = loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );
    }

    @Тогда("пользователь успешно входит в систему")
    public void dashboardShouldBeVisible() {
        context.dashboardPage.isOpened();
    }
}
