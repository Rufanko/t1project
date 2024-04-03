package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class CheckBoxes {
    @Step("Выделить первый чекбокс")
    public CheckBoxes selectCheckBoxById (String checkBoxid) {
        //запоминаем состояние чекбокса
        boolean checkBoxState = $(By.xpath("//*[text()=' checkbox 1']//input[" + checkBoxid + "]")).isSelected();

        $(By.xpath("//*[text()=' checkbox 1']//input[" + checkBoxid + "]")).click();
        //проверка что чекбокс отметился/снялся
        if (checkBoxState) {
            $(By.xpath("//*[text()=' checkbox 1']//input[" + checkBoxid + "]")).shouldNot(Condition.selected);
        } else {
            $(By.xpath("//*[text()=' checkbox 1']//input[" + checkBoxid + "]")).shouldBe(Condition.selected);
        }
        return page(CheckBoxes.class);
    }

    @Step
    public CheckBoxes selectCheckBox (int num) {
        if (num == 1) {
            selectCheckBoxById("1");
            selectCheckBoxById("2");
        }
        if (num == 2) {
            selectCheckBoxById("2");
            selectCheckBoxById("1");
        }
        return page(CheckBoxes.class);
    }
}


