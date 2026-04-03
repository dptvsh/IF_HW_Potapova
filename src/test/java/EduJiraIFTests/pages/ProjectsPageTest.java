package EduJiraIFTests.pages;

import EduJiraIFPages.LoginPage;
import EduJiraIFPages.ProjectsPage;
import EduJiraIFTests.WebHooks;
import com.codeborne.selenide.Selenide;
import config.ConfigReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProjectsPageTest extends WebHooks {
    private final LoginPage loginPage = new LoginPage();

    @Test
    @DisplayName("3. Проверка счетчика задач")
    public void checkIssuesCounterTest() {
        ProjectsPage projectsPage = loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password"))
                .goToProjectsPage()
                .goToAllTasks();

        int count = projectsPage.getTotalIssuesCount();
        projectsPage.clickButtonCreateNewIssue().createNewIssue();
        Selenide.refresh();
        int countUpdate = projectsPage.getTotalIssuesCount();

        Assertions.assertEquals(count + 1, countUpdate, "Количество задач не увеличилось на 1.");
    }
}
