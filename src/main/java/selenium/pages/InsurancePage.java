package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.testng.Reporter;
import selenium.actions.Actions;

public class InsurancePage extends BasePage<InsurancePage> {

    private static final String XPATH_PAYING_FOR_SELF = "//button[@class='primary-pair-button css-1ti498o e12r4e830']";

    @FindBy(how = How.XPATH, using = XPATH_PAYING_FOR_SELF)
    private WebElement payForSelf;

    public InsurancePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertTrue(Actions.isDisplayed(payForSelf));
    }

    public InsurancePage clickPayForSelf() {
        Actions.clickElement(payForSelf);
        Reporter.log("Clicked pay for self");
        return this;
    }
}
