package pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class CheckBoxes {
    @Step("Выделить первый чекбокс")
    public CheckBoxes selectFirstCheckBox () {
        $(By.xpath("//*[text()=' checkbox 1']/input")).click();
        return page(CheckBoxes.class);

    }

    @Step("Выделить первый чекбокс")
    public CheckBoxes selectSecondCheckBox () {
        $(By.xpath("//*[text()=' checkbox 2']/input")).click();
        return page(CheckBoxes.class);
    }

    @Step("Выделить первый чекбокс")
    public void soutCheckBoxesAttributes () {
        ElementsCollection checkBoxes = $$x(("//input"));
        checkBoxes.forEach(x -> System.out.println(x.getAttribute("checked")));
    }
}
