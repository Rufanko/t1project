package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class DisappearingElements {


    @Step(" Выбрать home")
    public DisappearingElements selecHome () {
        $(By.xpath("//a[text()='Home']']\n")).click();
        return page(DisappearingElements.class);
    }

    @Step(" Выбрать About")
    public DisappearingElements selecAbout () {
        $(By.xpath("//a[text()='About']")).click();
        return page(DisappearingElements.class);
    }

    @Step("Выбрать contactUs ")
    public DisappearingElements selectcontactUs () {
        $(By.xpath("//a[text()='Contact Us']")).click();
        return page(DisappearingElements.class);
    }

    @Step(" Выбрать Portfolio")
    public DisappearingElements selecHPortfolio () {
        $(By.xpath("//a[text()='Portfolio']")).click();
        return page(DisappearingElements.class);
    }

    @Step(" Выбрать Gallery")
    public DisappearingElements selecGallery () {
        $(By.xpath("//a[text()='Gallery']")).click();
        return page(DisappearingElements.class);
    }

}
