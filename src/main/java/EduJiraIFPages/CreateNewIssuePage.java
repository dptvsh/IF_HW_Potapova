package EduJiraIFPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;

public class CreateNewIssuePage {
    private final SelenideElement issueType = $x("//input[@id='issuetype-field']")
            .as("Поле 'Тип задачи'");
    private final SelenideElement project = $x("//input[@id='project-field']")
            .as("Поле 'Проект'");
    private final SelenideElement summary = $x("//input[@id='summary']")
            .as("Поле 'Тема'");
    private final SelenideElement description = $x("//label[@for='description']/following-sibling::div//iframe")
            .as("Поле 'Описание'");
    private final SelenideElement editableBody = $x("//body[@id='tinymce']")
            .as("Тело поля ввода");
    private final SelenideElement visualDescription = $x(
            "//label[@for='description']/following-sibling::div//li[@data-mode='wysiwyg']/child::button"
    ).as("Кнопка 'Визуальный' в описании");
    private final SelenideElement fixUnknownVer = $x(
            "//label[@for='fixVersions']/following-sibling::select//option[@value='-1']"
    ).as("Поле 'Исправить в версии'");
    private final SelenideElement priority = $x("//input[@id='priority-field']")
            .as("Поле 'Приоритет'");
    private final SelenideElement tags = $x("//textarea[@id='labels-textarea']")
            .as("Поле 'Метки'");
    private final SelenideElement environment = $x("//label[@for='environment']/following-sibling::div//iframe")
            .as("Поле 'Окружение'");
    private final SelenideElement visualEnvironment = $x(
            "//label[@for='environment']/following-sibling::div//li[@data-mode='wysiwyg']/child::button"
    ).as("Кнопка 'Визуальный' в окружении");
    private final SelenideElement affectedVersions = $x(
            "//label[@for='versions']/following-sibling::select//option[@value='-1']"
    ).as("Поле 'Затронуты версии'");
    private final SelenideElement relatedIssues = $x("//select[@id='issuelinks-linktype']")
            .as("Поле 'Связанные задачи'");
    private final SelenideElement issues = $x("//textarea[@id='issuelinks-issues-textarea']")
            .as("Поле 'Задача'");
    private final SelenideElement assignee = $x("//input[@id='assignee-field']")
            .as("Поле 'Исполнитель'");
    private final SelenideElement selectMeAssignee = $x("//button[@data-field-id='#assignee']")
            .as("Кнопка 'Назначить меня' в поле 'Исполнитель'");
    private final SelenideElement epic = $x("//input[@id='customfield_10100-field']")
            .as("Поле 'Ссылка на эпик'");
    private final SelenideElement sprint = $x("//input[@id='customfield_10104-field']")
            .as("Поле 'Спринт'");
    private final SelenideElement severity = $x("//select[@name='customfield_10400']")
            .as("Поле 'Серьезность'");
    private final SelenideElement severityTrivial = $x("//option[@value='10100']")
            .as("Пункт 'Тривиальный'");
    private final SelenideElement createButton = $x("//input[@id='create-issue-submit']")
            .as("Кнопка 'Создать'");
    private final SelenideElement successfulMessage = $x("//a[@class='issue-created-key issue-link']")
            .as("Сообщение об успешно созданной задаче");
    private final SelenideElement allTasks = $x("//a[@id='find_link']")
            .as("Раздел 'Задачи'");
    private final SelenideElement myOpenedTasks = $x("//a[@id='filter_lnk_my_lnk']")
            .as("Пункт 'Мои открытые задачи'");

    private void checkVisualButton(SelenideElement visualButton) {
        if (!"true".equals(visualButton.getAttribute("aria-pressed"))) {
            visualButton.click();
            visualButton.shouldHave(Condition.attribute("aria-pressed", "true"));
        }
    }

    public void checkVisualDescription() {
        checkVisualButton(visualDescription);
    }

    public void checkVisualEnvironment() {
        checkVisualButton(visualEnvironment);
    }

    public CreateNewIssuePage createNewIssue() {
        project.shouldHave(Condition.value("TEST"), Duration.ofSeconds(15));
        issueType.shouldHave(Condition.value("Ошибка"));
        summary.shouldBe(Condition.visible).setValue("Тема заполнена");
        checkVisualDescription();
        switchTo().frame(description);
        editableBody.shouldBe(Condition.visible).setValue("Описание заполнено");
        switchTo().defaultContent();
        fixUnknownVer.shouldBe(Condition.visible).click();
        priority.shouldHave(Condition.value("Medium"));
        tags.shouldBe(Condition.visible).setValue("Метка");
        checkVisualEnvironment();
        switchTo().frame(environment);
        editableBody.shouldBe(Condition.visible).setValue("Окружение заполнено");
        switchTo().defaultContent();
        affectedVersions.shouldBe(Condition.visible).click();
        relatedIssues.shouldHave(Condition.value("blocks"));
        issues.shouldBe(Condition.visible).shouldBe(Condition.empty);
        assignee.shouldHave(Condition.value("Автоматически"));
        selectMeAssignee.shouldBe(Condition.visible).click();
        sprint.shouldBe(Condition.visible).shouldBe(Condition.empty);
        epic.shouldBe(Condition.visible).shouldBe(Condition.empty);
        severity.shouldBe(Condition.visible).click();
        severityTrivial.shouldBe(Condition.visible).click();
        createButton.shouldBe(Condition.visible).click();
        successfulMessage.shouldBe(Condition.visible);
        return this;
    }

    public boolean isCreated() {
        return successfulMessage.isDisplayed();
    }

    public BrowsePage goToBrowsePage() {
        allTasks.shouldBe(Condition.visible).click();
        myOpenedTasks.shouldBe(Condition.visible).click();
        return new BrowsePage();
    }
}
