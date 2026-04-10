package EduJiraIFTests.pages;

import EduJiraIFPages.LoginPage;
import EduJiraIFPages.ProjectsPage;
import EduJiraIFTests.WebHooks;
import config.ConfigReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DashboardPageTest extends WebHooks {
    private final LoginPage loginPage = new LoginPage();

    @Test
    @DisplayName("2. Переход в проект 'TEST'")
    @Tag("HW3")
    public void goToProjectPageTest() {
        ProjectsPage projectsPage = loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        ).goToProjectsPage();

        Assertions.assertTrue(projectsPage.isOpened(), "Не открылась страница проекта 'TEST'.");
    }
}
