package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.testng.Reporter;
import selenium.actions.Actions;

import static selenium.utilities.DriverFactory.getDriver;

public class HomePage extends BasePage<HomePage> {

    private static final String HOME_PAGE_URL = "https://plushcare.com/";
    private static final String CSS_APPOINTMENT_BUTTON = "[href^='https://www.plushcare.com/profile/book/method/']";

    @FindBy(how = How.CSS, using = CSS_APPOINTMENT_BUTTON)
    private WebElement appointmentButton;

    public HomePage(WebDriver driver) { super(driver); }

    @Override
    protected void load() {
        getDriver().get(HOME_PAGE_URL);
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertEquals(getDriver().getCurrentUrl(), HOME_PAGE_URL);
    }

    public HomePage clickAppointment() {
        Actions.clickElement(appointmentButton);
        Reporter.log("Clicked appointment button");
        return this;
    }

}
