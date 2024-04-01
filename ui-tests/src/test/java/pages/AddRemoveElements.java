package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.*;

public class AddRemoveElements {
    @Step
    public AddRemoveElements clickAddElement () {
        $x("//button[@onclick='addElement()']").click();
        return page(AddRemoveElements.class);
    }

    @Step("Перейти на страницу Add/Remove Elements. Нажать на кнопку Add Element 5 раз. С каждым нажатием выводить в консоль текст появившегося элемента.")
    public AddRemoveElements clickAndSoutText (int num) {
        for (int i = 1; i <= num; i++) {
            clickAddElement();
            System.out.println($x("//button[@class='added-manually'][" + i + "]").getText());
            int count = $$x("//button[@class='added-manually']").size();
            Assertions.assertEquals(i, count);
        }

        return page(AddRemoveElements.class);

    }


    @Step("Нажать на разные кнопки Delete три раза. Выводить в консоль оставшееся количество кнопок Delete и их тексты")
    public void clickDelete (int num) {

        ElementsCollection coll = $$x("//button[@class='added-manually']");
        for (int i = 0; i < num; i++) {
            int count = $$x("//button[@class='added-manually']").size();
            coll.get(i).click();
            int count2 = $$x("//button[@class='added-manually']").size();
            --count;
            Assertions.assertEquals(count, count2);

            coll = $$x("//button[@class='added-manually']");
            System.out.println(coll.size());
            for (SelenideElement e : coll) {
                System.out.println(e.getText());
            }
        }
    }


}
