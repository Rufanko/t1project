
import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    @BeforeEach
    public void option () {


        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\.m2\\repository\\webdriver\\chromedriver\\win32\\114.0.5735.90\\chromedriver.exe");
        Configuration.baseUrl = ("https://the-internet.herokuapp.com/");
        Configuration.timeout = 15000;
        open(baseUrl);
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }

    @AfterEach
    public void close () {
        closeWindow();
    }
}
