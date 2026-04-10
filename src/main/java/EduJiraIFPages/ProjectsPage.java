package EduJiraIFPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectsPage {
    private final SelenideElement createIssue = $x("//a[@id='create_link']")
            .as("Создание новой задачи");
    private final SelenideElement taskCounter = $x("//div[@class='showing']")
            .as("Счетчик задач");
    private final SelenideElement changeFilter = $x("//button[@id='subnav-trigger']")
            .as("Кнопка 'Сменить фильтр'");
    private final SelenideElement allTasks = $x("//a[@data-item-id='allissues']")
            .as("Фильтр 'Все задачи'");
    private final SelenideElement projectsHeader = $x("//header[@class='aui-page-header issue-search-header']")
            .as("Хэдер задач");
    private final SelenideElement allTasksTitle = $x("//span[@id='issues-subnavigation-title']")
            .as("Текст 'Все задачи'");

    public boolean isOpened() {
        return projectsHeader.isDisplayed();
    }

    public ProjectsPage goToAllTasks() {
        String oldCounter = taskCounter.getText();
        changeFilter.shouldBe(Condition.visible).click();
        allTasks.shouldBe(Condition.visible).click();
        allTasksTitle.shouldBe(Condition.visible)
                .shouldHave(Condition.textCaseSensitive("Все задачи"));
        taskCounter.shouldNotHave(Condition.exactText(oldCounter), Duration.ofSeconds(5));
        return this;
    }

    public CreateNewIssuePage clickButtonCreateNewIssue() {
        createIssue.shouldBe(Condition.visible).click();
        return new CreateNewIssuePage();
    }

    public int getTotalIssuesCount() {
        String counterText = taskCounter.shouldBe(Condition.visible, Duration.ofSeconds(10)).getText();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(counterText);
        ArrayList<String> numbers = new ArrayList<>();
        while (matcher.find()) {
            numbers.add(matcher.group());
        }
        String totalCount = numbers.getLast();
        return Integer.parseInt(totalCount);
    }
}
