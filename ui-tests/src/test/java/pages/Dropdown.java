package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class Dropdown {

    @Step(" Выбрать первую опцию")
    public Dropdown selectFirstOption () {
        $(By.xpath("//select[@id='dropdown']\n")).click();
        $(By.xpath("//option[@value='1']")).click();
        //Проверять корректное состояние каждого dropDown после каждого нажатия на него.
        Assertions.assertEquals("Option 1", $(By.xpath("//option[@selected='selected']")).getText());
        return page(Dropdown.class);
    }

    @Step(" Выбрать втрую опцию")
    public Dropdown selectSecondOption () {
        $(By.xpath("//select[@id='dropdown']\n")).click();
        $(By.xpath("//option[@value='2']")).click();
        //Проверять корректное состояние каждого dropDown после каждого нажатия на него.
        Assertions.assertEquals("Option 2", $(By.xpath("//option[@selected='selected']")).getText());
        return page(Dropdown.class);
    }

    @Step("вывести в консоль текущий текст элемента dropdown")
    public Dropdown outSelectedText () {
        System.out.println($(By.xpath("//option[@selected='selected']")).getText());
        return page(Dropdown.class);
    }


}
