package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class AddRemoveElements {
    @Step
    public AddRemoveElements clickAddElement () {
        $x("//button[@onclick='addElement()']").click();
        return page(AddRemoveElements.class);
    }

    @Step("Перейти на страницу Add/Remove Elements. Нажать на кнопку Add Element 5 раз. С каждым нажатием выводить в консоль текст появившегося элемента.")
    public AddRemoveElements clickAndSoutText () {
        for (int i = 1; i <= 5; i++) {
            clickAddElement();
            System.out.println($x("//button[@class='added-manually'][" + i + "]").getText());
        }
        return page(AddRemoveElements.class);

    }


    @Step("Нажать на разные кнопки Delete три раза. Выводить в консоль оставшееся количество кнопок Delete и их тексты")
    public void clickDelete () {

        ElementsCollection coll = $$x("//button[@class='added-manually']");
        for (int i = 0; i < 3; i++) {
            coll.get(i).click();
            coll = $$x("//button[@class='added-manually']");
            System.out.println(coll.size());
            for (SelenideElement e : coll) {
                System.out.println(e.getText());
            }
        }
    }

}
