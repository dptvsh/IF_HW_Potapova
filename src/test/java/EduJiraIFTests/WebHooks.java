package EduJiraIFTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.ConfigReader;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;

public class WebHooks {

    @BeforeAll
    public static void setUpAllure() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(false)
        );
    }

    @BeforeEach
    public void setUp() {
        Configuration.pageLoadStrategy = PageLoadStrategy.EAGER.toString();
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Selenide.open(ConfigReader.get("base.url"));
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
