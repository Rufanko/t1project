package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Hovers {

    @Step("Навести курсор и вывести инфу")
    public void hoverImage(){
        ElementsCollection elements = $$x("//div[@class='figure']//img");
        for (SelenideElement e: elements){
            e.hover();
            System.out.println($x("//div[@class='figcaption']//h5").getText());
        }
    }
}
