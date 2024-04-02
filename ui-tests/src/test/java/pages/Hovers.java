package pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Hovers {

    @Step("Навести курсор и вывести инфу")
    public void hoverImage () {
        ElementsCollection elements = $$x("//div[@class='figure']");
        for (int i = 0; i < elements.size(); i++) {
            System.out.println(elements.get(i).hover().getText());
        }
    }
}
