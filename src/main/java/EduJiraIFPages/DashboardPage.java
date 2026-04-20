package EduJiraIFPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import config.ConfigReader;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {
    private final SelenideElement profile = $x("//span[@class='aui-avatar aui-avatar-small']")
            .as("Аватар профиля");
    private final SelenideElement projects = $x("//a[@id='browse_link']")
            .as("Раздел 'Проекты'");
    private final SelenideElement test = $x("//li[@id='admin_main_proj_link']")
            .as("Проект 'TEST'");
    private final SelenideElement searchField = $x("//input[@id='quickSearchInput']")
            .as("Поле быстрого поиска");

    public DashboardPage shouldBeVisible() {
        profile.shouldBe(Condition.visible, Duration.ofSeconds(5));
        return this;
    }

    public ProjectsPage goToProjectsPage() {
        projects.shouldBe(Condition.visible).click();
        test.shouldBe(Condition.visible).click();
        return new ProjectsPage();
    }

    public BrowsePage findTestSelenium() {
        searchField.shouldBe(Condition.visible).setValue(ConfigReader.get("task")).pressEnter();
        return new BrowsePage();
    }
}
