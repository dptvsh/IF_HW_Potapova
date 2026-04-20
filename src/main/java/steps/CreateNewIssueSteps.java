package steps;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;

public class CreateNewIssueSteps {

    private final ScenarioContext context;

    public CreateNewIssueSteps(ScenarioContext context) {
        this.context = context;
    }

    @Когда("пользователь создает новый баг")
    public void createNewBug() {
        context.newIssuePage = context.projectsPage.clickButtonCreateNewIssue().createNewIssue();
    }

    @Тогда("баг успешно создан")
    public void bugIsCreated() {
        Assertions.assertTrue(context.newIssuePage.isCreated(), "Баг не был создан.");
    }

    @Когда("пользователь переходит к списку своих открытых задач")
    public void goToMyOpenTasks() {
        context.browsePage = context.newIssuePage.goToBrowsePage();
    }

    @И("^переводит созданный баг в статус '(.*)'")
    public void changeStatus(String str) {
        context.browsePage.doneIssue();
    }

    @Тогда("^статус задачи изменён на '(.*)'")
    public void statusShouldBeDone(String status) {
        context.browsePage.waitForStatus(status, 10);
    }
}
