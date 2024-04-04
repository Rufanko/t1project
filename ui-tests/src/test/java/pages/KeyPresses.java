package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class KeyPresses {

    @Step()
    public KeyPresses send () {
        Actions builder = new Actions(getWebDriver());
        builder.sendKeys("A").perform();
        $x("//*[@id ='result'][contains(text(),'A')]").should(Condition.exist);
        builder.sendKeys("X").perform();
        $x("//*[@id ='result'][contains(text(),'X')]").should(Condition.exist);
        builder.sendKeys("B").perform();
        $x("//*[@id ='result'][contains(text(),'B')]").should(Condition.exist);
        builder.sendKeys("C").perform();
        $x("//*[@id ='result'][contains(text(),'C')]").should(Condition.exist);
        builder.sendKeys("D").perform();
        $x("//*[@id ='result'][contains(text(),'D')]").should(Condition.exist);
        builder.sendKeys("E").perform();
        $x("//*[@id ='result'][contains(text(),'E')]").should(Condition.exist);
        builder.sendKeys("F").perform();
        $x("//*[@id ='result'][contains(text(),'F')]").should(Condition.exist);
        builder.sendKeys("G").perform();
        $x("//*[@id ='result'][contains(text(),'G')]").should(Condition.exist);
        builder.sendKeys("h").perform();
        $x("//*[@id ='result'][contains(text(),'H')]").should(Condition.exist);
        builder.sendKeys("j").perform();
        $x("//*[@id ='result'][contains(text(),'J')]").should(Condition.exist);
        builder.sendKeys(Keys.ENTER).perform();
        $x("//*[@id ='result'][contains(text(),'ENTER')]").should(Condition.exist);
        builder.sendKeys(Keys.BACK_SPACE).perform();
        $x("//*[@id ='result'][contains(text(),'BACK_SPACE')]").should(Condition.exist);
        builder.sendKeys(Keys.CONTROL).perform();
        $x("//*[@id ='result'][contains(text(),'CONTROL')]").should(Condition.exist);
        builder.sendKeys(Keys.ALT).perform();
        $x("//*[@id ='result'][contains(text(),'ALT')]").should(Condition.exist);
        builder.sendKeys(Keys.TAB).perform();
        $x("//*[@id ='result'][contains(text(),'TAB')]").should(Condition.exist);

        return page(KeyPresses.class);
    }
}
