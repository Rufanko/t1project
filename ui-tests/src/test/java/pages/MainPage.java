package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class MainPage {

    @Step("Перейти на страницу Checkboxes")
    public CheckBoxes openCheckBoxes () {
        $(By.xpath("//a[text()='Checkboxes']")).click();
        return page(CheckBoxes.class);
    }

    @Step("Перейти на страницу Dropdown")
    public Dropdown openDropdown () {
        $(By.xpath("//a[text()='Dropdown']")).click();
        return page(Dropdown.class);
    }

    @Step("Перейти на страницу Disappearing Elements")
    public DisappearingElements openDisappearingElements () {
        $(By.xpath("//a[text()='Disappearing Elements']")).click();
        return page(DisappearingElements.class);
    }

    @Step("Перейти на страницу Inputs")
    public Inputs openInputs () {
        $(By.xpath("//a[text()='Inputs']")).click();
        return page(Inputs.class);
    }

    @Step("Перейти на страницу Checkboxes")
    public Hovers openHovers () {
        $(By.xpath("//a[text()='Hovers']")).click();
        return page(Hovers.class);
    }

    @Step("Перейти на страницу Notification Message")
    public NotificationMessage openNotificationMessage () {
        $(By.xpath("//a[text()='Notification Messages']")).click();
        return page(NotificationMessage.class);
    }

    @Step("Перейти на страницу  Add/Remove Elements")
    public AddRemoveElements openAddRemoveElements () {
        $(By.xpath("//a[text()='Add/Remove Elements']")).click();
        return page(AddRemoveElements.class);
    }

    @Step("Перейти на страницу Notification Message")
    public StatusCodes openStatusCodes () {
        $(By.xpath("//a[text()='Status Codes']")).click();
        return page(StatusCodes.class);
    }

    @Step("Перейти на страницу Notification Message")
    public DragAndDrop openDragAndDrop() {
        $(By.xpath("//a[text()='Drag and Drop']")).click();
        return page(DragAndDrop.class);
    }


}
