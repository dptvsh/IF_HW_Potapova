package EduJiraIFTests.jiraPagesTests;

import EduJiraIFPages.CreateNewIssuePage;
import EduJiraIFPages.LoginPage;
import EduJiraIFTests.WebHooks;
import config.ConfigReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CreateNewIssuePageTest extends WebHooks {
    private final LoginPage loginPage = new LoginPage();

    @Test
    @DisplayName("5. Создание нового бага и перевод его в статус 'Выполнено'")
    @Tag("HW3")
    public void checkTask() {
        CreateNewIssuePage newIssuePage = loginPage.login(
                        ConfigReader.get("username"),
                        ConfigReader.get("password"))
                .goToProjectsPage()
                .clickButtonCreateNewIssue()
                .createNewIssue();

        Assertions.assertTrue(newIssuePage.isCreated(),
                "Баг не был создан.");

        newIssuePage.goToBrowsePage().doneIssue();
    }
}
