import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    @BeforeEach
    public void option () {
        Configuration.baseUrl = ("https://the-internet.herokuapp.com/");
        Configuration.timeout = 15000;

        open(baseUrl);
    }
}
