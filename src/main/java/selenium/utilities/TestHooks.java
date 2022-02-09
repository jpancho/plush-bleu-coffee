package selenium.utilities;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

public class TestHooks extends DriverFactory {

    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        startDriver();
        Reporter.log("Starting driver", true);
    }

    @AfterMethod(alwaysRun = true)
    public void afterTest() {
        getDriver().close();
        Reporter.log("Closing driver", true);
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        destroyDriver();
        Reporter.log("Destroying all drivers", true);
    }
}
