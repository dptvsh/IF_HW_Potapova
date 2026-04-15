package steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;

public class DashboardSteps {

    private final ScenarioContext context;

    public DashboardSteps(ScenarioContext context) {
        this.context = context;
    }

    @Когда("пользователь переходит в проект 'TEST'")
    public void goToTestProject() {
        context.projectsPage = context.dashboardPage.goToProjectsPage();
    }

    @Тогда("открывается страница проекта 'TEST'")
    public void projectPageShouldBeOpened() {
        context.projectsPage.isOpened();
    }
}
