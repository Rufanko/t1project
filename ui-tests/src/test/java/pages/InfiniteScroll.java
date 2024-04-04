package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class InfiniteScroll {
    @Step
    public InfiniteScroll scrollUntilElem () {
        SelenideElement e = $x("//*[contains(text(),'Eius')]");
        Actions builder = new Actions(getWebDriver());
        builder.scrollToElement(e).perform();
        e.shouldBe(Condition.visible);
        return page(InfiniteScroll.class);
    }
}
