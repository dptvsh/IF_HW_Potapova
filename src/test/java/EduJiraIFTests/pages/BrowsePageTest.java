package EduJiraIFTests.pages;

import EduJiraIFPages.BrowsePage;
import EduJiraIFPages.LoginPage;
import EduJiraIFTests.WebHooks;
import config.ConfigReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class BrowsePageTest extends WebHooks {
    private final LoginPage loginPage = new LoginPage();

    @Test
    @DisplayName("4. Проверка полей задачи 'TestSeleniumATHomework'")
    @Tag("HW3")
    public void checkTask() {
        BrowsePage browsePage = loginPage.login(
                        ConfigReader.get("username"),
                        ConfigReader.get("password"))
                .shouldBeVisible()
                .findTest(ConfigReader.get("task"));

        Assertions.assertEquals("сделать", browsePage.getStatus().toLowerCase(),
                "Статус задачи не соответствует ожидаемому.");
        Assertions.assertEquals("Version 2.0", browsePage.getFixVersion(),
                "Значение поля 'Исправить в версиях' не соответствует ожидаемому.");
    }
}
