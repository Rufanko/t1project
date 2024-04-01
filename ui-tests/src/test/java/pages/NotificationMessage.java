package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class NotificationMessage {
    private SelenideElement e;

    @Step("Кликать на кнопку и закрывать всплывающее окно")
    public void clickOnMessage () {

        $x("//a[text()='Click here']").click();
        e = $x("//div[@id='flash']");
        Assertions.assertEquals(("Action successful\n" +
                "×"), e.getText());

    }


    @Step("Закрыть сообщение")
    public NotificationMessage close () {
        $x("//a[@class='close']").click();
        return page(NotificationMessage.class);
    }
}
