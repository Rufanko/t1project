package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class DragAndDrop {

    @Step("Перетащить элемент B на элемент А")
    public DragAndDrop dragAndDropElementAToB () {
        SelenideElement toElement = $x("//*[@id='column-a']");
        SelenideElement fromElement = $x("//*[@id='column-b']");
        Actions builder = new Actions(getWebDriver());

        Action dragAndDrop = builder.clickAndHold(fromElement)
                .moveToElement(toElement)
                .release(toElement)
                .build();
        dragAndDrop.perform();

        Assertions.assertEquals($x("//div[@class='column'][1]").getText(), "B");
        Assertions.assertEquals($x("//div[@class='column'][2]").getText(), "A");
        return page(DragAndDrop.class);
    }
}
