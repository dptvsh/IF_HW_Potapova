package EduJiraIFTests.pages;

import EduJiraIFPages.DashboardPage;
import EduJiraIFPages.LoginPage;
import EduJiraIFTests.WebHooks;
import config.ConfigReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginTest extends WebHooks {
    private final LoginPage loginPage = new LoginPage();

    @Test
    @DisplayName("1. Авторизация")
    public void loginTest() {
        DashboardPage dashboardPage = loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );

        Assertions.assertTrue(dashboardPage.isOpened(), "После авторизации не открылась страница Dashbord.");
    }
}
