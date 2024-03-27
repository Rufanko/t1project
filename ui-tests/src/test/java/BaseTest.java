import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;

public class BaseTest {
    @BeforeEach
    public void option () {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\.m2\\repository\\webdriver\\chromedriver\\win32\\114.0.5735.90\\chromedriver.exe");
        Configuration.baseUrl = ("https://the-internet.herokuapp.com/");
        Configuration.timeout = 15000;
        open(baseUrl);
    }

    @AfterEach
    public void close() {
        Selenide.close();
    }
}
