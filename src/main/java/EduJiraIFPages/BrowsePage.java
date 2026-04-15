package EduJiraIFPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class BrowsePage {
    private final SelenideElement businessProcess = $x("//a[@id='opsbar-transitions_more']")
            .as("Кнопка 'Бизнес-процессы'");
    private final SelenideElement doneButton = $x("//aui-item-link[@id='action_id_31']")
            .as("Кнопка 'Выполнено'");
    private final SelenideElement status = $x("//span[@id='status-val']")
            .as("Статус задачи");
    private final SelenideElement fixVersions = $x("//span[@id='fixfor-val']")
            .as("Поле 'Исправить в версиях'");

    public String getStatus() {
        return status.shouldBe(Condition.visible).getText();
    }

    public void waitForStatus(String expectedStatus, int seconds) {
        status.shouldHave(Condition.text(expectedStatus), Duration.ofSeconds(seconds));
    }

    public String getFixVersion() {
        return fixVersions.shouldBe(Condition.visible).getText();
    }

    public void doneIssue() {
        businessProcess.shouldBe(Condition.visible).click();
        doneButton.shouldBe(Condition.visible).click();
    }
}
