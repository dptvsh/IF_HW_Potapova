package EduJiraIFPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private final SelenideElement usernameInput = $x("//input[@id='login-form-username']")
            .as("Поле ввода 'Имя пользователя'");
    private final SelenideElement passwordInput = $x("//input[@id='login-form-password']")
            .as("Поле ввода 'Пароль'");
    private final SelenideElement loginButton = $x("//input[@id='login']")
            .as("Кнопка 'Войти'");

    public void isOpened() {
        usernameInput.shouldBe(Condition.visible, Duration.ofSeconds(10));
        passwordInput.shouldBe(Condition.visible, Duration.ofSeconds(10));
        loginButton.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public DashboardPage login(String username, String password) {
        usernameInput.shouldBe(Condition.visible).setValue(username);
        passwordInput.shouldBe(Condition.visible).setValue(password);
        loginButton.shouldBe(Condition.enabled).click();
        return new DashboardPage();
    }
}
