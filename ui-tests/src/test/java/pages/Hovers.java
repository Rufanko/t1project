package pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.$$x;

public class Hovers {

    @Step("Навести курсор и вывести инфу")
    public void hoverImage (int howerID) {
        ElementsCollection elements = $$x("//div[@class='figure']");
        String hoverText = elements.get(howerID).hover().getText();
        int userId = howerID+1;
        Assertions.assertEquals("name: user" + userId + "\n" +
                "View profile", hoverText);

    }
}
