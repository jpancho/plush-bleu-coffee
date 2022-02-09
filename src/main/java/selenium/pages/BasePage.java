package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public abstract class BasePage <T extends LoadableComponent<T>> extends LoadableComponent<T> {

    protected BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}