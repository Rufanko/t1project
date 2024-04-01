package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class Inputs {
    @Step(" Кликнуть на инпут")
    public Inputs inputRandomNumber () {
        SelenideElement input = $(By.xpath("//input"));
        input.click();
        int randomNumber = TestHelperUI.generateToNumbers(1, 10000);
        input.sendKeys(String.valueOf(randomNumber));
        Assertions.assertEquals(String.valueOf(randomNumber), input.getValue());
        System.out.println(input.getValue());
        return page(Inputs.class);
    }

    @Step(" Кликнуть на инпут")
    public Inputs inputRandomNumber (String symbol) {

        SelenideElement input = $(By.xpath("//input"));
        input.click();
        int randomNumber = TestHelperUI.generateToNumbers(1, 10000);
        input.sendKeys(symbol + randomNumber + symbol);
        Assertions.assertEquals(String.valueOf(randomNumber), input.getValue());
        System.out.println(input.getValue());
        return page(Inputs.class);
    }

}
