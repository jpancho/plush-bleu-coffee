package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import selenium.actions.Actions;

public class CreateProfilePage extends BasePage<CreateProfilePage> {

    private static final String XPATH_CREATE_PROFILE = "//*[@class='booking-registration css-1bpg98l eov34z70']";

    @FindBy(how = How.XPATH, using = XPATH_CREATE_PROFILE)
    private WebElement createProfile;

    public CreateProfilePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertTrue(Actions.isDisplayed(createProfile));
    }
}
