package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class NotificationMessage {
    private SelenideElement e;

    @Step
    public void clickOnMessage () {
        while (true) {
            $x("//a[text()='Click here']").click();
            e = $x("//div[@id='flash']");
            if (e.getText().equals("Action successful\n" +
                    "×")) {
                break;

            } else {
                close();
            }

        }
    }


    @Step("Закрыть сообщение")
    public NotificationMessage close () {
        $x("//a[@class='close']").click();
        return page(NotificationMessage.class);
    }
}
