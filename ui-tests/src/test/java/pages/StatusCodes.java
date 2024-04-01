package pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.*;


public class StatusCodes {
    static ElementsCollection coll = $$x("//li/a");

    @Step("")
    public StatusCodes clickStatuses () throws InterruptedException {
        for (int i = 1; i <= 4; i++) {
            $x("//li[" + i + "]/a").click();
            if (i == 1) {
                Assertions.assertTrue($x("//p").getText().contains("200"));
            }
            if (i == 2) {
                Assertions.assertTrue($x("//p").getText().contains("301"));
            }
            if (i == 3) {
                Assertions.assertTrue($x("//p").getText().contains("404"));
            }
            if (i == 4) {
                Assertions.assertTrue($x("//p").getText().contains("500"));
            }
            System.out.println($x("//p").getText());
            open("https://the-internet.herokuapp.com/status_codes");
        }
        return page(StatusCodes.class);
    }
}
