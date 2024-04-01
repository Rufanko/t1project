import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.MainPage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Tests extends BaseTest {
    private final String baseUrl = "https://the-internet.herokuapp.com/";

    static Stream<Arguments> getNumOfDeleteAndAdd () {
        return Stream.of(
                arguments(2, 1),
                arguments(5, 2),
                arguments(1, 3)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("Перейти на страницу Checkboxes. Выделить первый чекбокс, снять выделение со второго чекбокса. Вывести в консоль состояние атрибута checked для каждого чекбокса.")
    public void firstTest (int number) {
        new MainPage()
                .openCheckBoxes()
                .selectCheckBox(number);
    }

    @Test
    @DisplayName("Перейти на страницу Dropdown. Выбрать первую опцию, вывести в консоль текущий текст элемента dropdown")
    public void test2 () {
        new MainPage()
                .openDropdown()
                .selectFirstOption();
    }

    @Test
    @DisplayName("Перейти на страницу Dropdown. Выбрать вторую опцию, вывести в консоль текущий текст элемента dropdown")
    public void test3 () {
        new MainPage()
                .openDropdown()
                .selectSecondOption()
                .outSelectedText();
    }

    @RepeatedTest(value = 10)
    @DisplayName("Перейти на страницу Disappearing Elements. Добиться отображения 5 элементов, максимум за 10 попыток, если нет, провалить тест с ошибкой.")
    public void test4 () {
        MainPage mainPage = new MainPage();
        int elements = mainPage.openDisappearingElements().countElemenst();
        Assertions.assertEquals(5, elements);
    }

    @TestFactory
    @DisplayName("Перейти на страницу Inputs. Ввести любое случайное число от 1 до 10 000. Вывести в консоль значение элемента Input.")
    List<DynamicTest> testinputs () {
        List<DynamicTest> dt = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            dt.add(DynamicTest.dynamicTest("Dynamic test " + i, () -> {
                open(baseUrl);
                new MainPage().openInputs().inputRandomNumber();
            }));
        }
        return dt;
    }

    @TestFactory
    @DisplayName("Негатвиные сценарии.Перейти на страницу Inputs. Ввести любое случайное число от 1 до 10 000. Вывести в консоль значение элемента Input.")
    List<DynamicTest> testinputsNegative () {
        List<String> symbols = new ArrayList<>();
        symbols.add(" ");
        symbols.add("test");
        symbols.add("/]/");
        symbols.add("##$#$!#");
        symbols.add("");
        List<DynamicTest> dt = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            final int j = i;
            dt.add(DynamicTest.dynamicTest("Dynamic test " + i, () -> {
                open(baseUrl);
                new MainPage().openInputs().inputRandomNumber(symbols.get(j));
            }));
        }
        return dt;
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    @DisplayName("Перейти на страницу Hovers. Навести курсор на каждую картинку. Вывести в консоль текст, который появляется при наведении.")
    public void test6 (int hoverId) {
        new MainPage()
                .openHovers()
                .hoverImage(hoverId);
    }

    @RepeatedTest(value = 3)
    @DisplayName("Перейти на страницу Notification Message. Кликать до тех пор, пока не покажется уведомление Action successful. После каждого неудачного клика закрывать всплывающее уведомление.")
    public void test7 () {
        new MainPage()
                .openNotificationMessage()
                .clickOnMessage();
    }

    @ParameterizedTest()
    @MethodSource("getNumOfDeleteAndAdd")
    @DisplayName("Перейти на страницу Add/Remove Elements. Нажать на кнопку Add Element 5 раз. С каждым нажатием выводить в консоль текст появившегося элемента. Нажать на разные кнопки Delete три раза. Выводить в консоль оставшееся количество кнопок Delete и их тексты.")
    public void test8 (int addNum, int deleteNum) {
        new MainPage()
                .openAddRemoveElements()
                .clickAndSoutText(addNum)
                .clickDelete(deleteNum);
    }

    @Test
    @DisplayName("Перейти на страницу Status Codes. Кликнуть на каждый статус в новом тестовом методе, вывести на экран текст после перехода на страницу статус")
    public void test9 () throws InterruptedException {
        new MainPage()
                .openStatusCodes()
                .clickStatuses();
    }

}
