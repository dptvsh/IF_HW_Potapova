package steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;

public class ProjectSteps {

    private final ScenarioContext context;
    int beforeCount;

    public ProjectSteps(ScenarioContext context) {
        this.context = context;
    }

    @Когда("пользователь переключает фильтр на 'Все задачи'")
    public void switchToAllTasks() {
        context.projectsPage.goToAllTasks();
    }

    @И("пользователь запоминает счетчик задач")
    public void rememberCurrentIssueCount() {
        beforeCount = context.projectsPage.getTotalIssuesCount();
    }

    @И("пользователь создает новую задачу")
    public void createNewBug() {
        context.projectsPage.clickButtonCreateNewIssue().createNewIssue();
    }

    @И("пользователь обновляет страницу")
    public void refreshPage() {
        Selenide.refresh();
    }

    @Тогда("счетчик задач увеличивается на 1")
    public void issuesCountIncreased() {
        int afterCount = context.projectsPage.getTotalIssuesCount();
        Assertions.assertEquals(beforeCount + 1, afterCount, "Количество задач не увеличилось на 1.");
    }

}
