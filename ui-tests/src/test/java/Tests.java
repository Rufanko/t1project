import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

public class Tests extends BaseTest {

    @Test
    @DisplayName("Перейти на страницу Checkboxes. Выделить первый чекбокс, снять выделение со второго чекбокса. Вывести в консоль состояние атрибута checked для каждого чекбокса.")
    public void firstTest () {
        new MainPage()
                .openCheckBoxes()
                .selectFirstCheckBox()
                .soutCheckBoxesAttributes();
    }

    @Test
    @DisplayName("Перейти на страницу Dropdown. Выбрать первую опцию, вывести в консоль текущий текст элемента dropdown")
    public void test2 () {
        new MainPage().openDropdown().selectFirstOption().outSelectedText();
    }

    @Test
    @DisplayName("Перейти на страницу Dropdown. Выбрать вторую опцию, вывести в консоль текущий текст элемента dropdown")
    public void test3 () {
        new MainPage()
                .openDropdown()
                .selectSecondOption()
                .outSelectedText();
    }
}
