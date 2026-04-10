package EduJiraIFTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import config.ConfigReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;

public class WebHooks {

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
