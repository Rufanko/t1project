package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class Inputs {
    @Step(" Кликнуть на инпут")
    public Inputs inputRandomNumber () {
        SelenideElement input = $(By.xpath("//input"));
        input.click();
        input.sendKeys(String.valueOf(TestHelperUI.generateToNumbers(1, 10000)));
        System.out.println(input.getValue());
        return page(Inputs.class);
    }
}
