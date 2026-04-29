package EduJiraIFPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

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

    @Step("Проверить, что страница открыта")
    public boolean isOpened() {
        try {
            profile.shouldBe(Condition.visible, Duration.ofSeconds(10));
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }

    @Step("Проверить, что пользователь авторизован")
    public DashboardPage shouldBeVisible() {
        profile.shouldBe(Condition.visible, Duration.ofSeconds(5));
        return this;
    }

    @Step("Перейти на страницу проекта")
    public ProjectsPage goToProjectsPage() {
        projects.shouldBe(Condition.visible).click();
        test.shouldBe(Condition.visible).click();
        return new ProjectsPage();
    }

    @Step("Найти задачу {task}")
    public BrowsePage findTest(String task) {
        searchField.shouldBe(Condition.visible).setValue(task).pressEnter();
        return new BrowsePage();
    }
}
