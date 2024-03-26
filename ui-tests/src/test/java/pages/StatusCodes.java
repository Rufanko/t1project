package pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;


public class StatusCodes {
    static ElementsCollection coll = $$x("//li/a");

    @Step("")
    public StatusCodes clickStatuses () throws InterruptedException {
        for (int i = 1; i <= 4; i++) {
            $x("//li[" + i + "]/a").click();
            System.out.println($x("//p").getText());
            open("https://the-internet.herokuapp.com/status_codes");
        }
        return page(StatusCodes.class);
    }
}
