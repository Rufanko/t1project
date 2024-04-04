package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class ContextMenu {

    public ContextMenu clickContextMenu () {
        SelenideElement e = $x("//*[@id='hot-spot']");
        e.contextClick();
        String contextMenu = Selenide.switchTo().alert().getText();
        Assertions.assertEquals("You selected a context menu", contextMenu);
        return page(ContextMenu.class);
    }
}
