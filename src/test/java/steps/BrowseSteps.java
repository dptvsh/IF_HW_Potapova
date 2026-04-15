package steps;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;

public class BrowseSteps {

    private final ScenarioContext context;

    public BrowseSteps(ScenarioContext context) {
        this.context = context;
    }

    @Когда("^пользователь находит задачу '(.*)'")
    public void findTask(String testName) {
        context.browsePage = context.dashboardPage.findTestSelenium();
    }

    @Тогда("^статус задачи равен '(.*)'")
    public void checkStatus(String status) {
        Assertions.assertEquals("сделать", context.browsePage.getStatus().toLowerCase(),
                "Статус задачи не соответствует ожидаемому.");
    }

    @И("^поле 'Исправить в версиях' содержит '(.*)'")
    public void checkFixVersions(String fixVersion) {
        Assertions.assertEquals("Version 2.0", context.browsePage.getFixVersion(),
                "Значение поля 'Исправить в версиях' не соответствует ожидаемому.");
    }
}
