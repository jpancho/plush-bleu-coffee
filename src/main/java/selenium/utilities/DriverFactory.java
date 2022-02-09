package selenium.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory extends Environment {

    protected WebDriver driver;
    private static final ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return drivers.get();
    }

    public void startDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        addDriver(new ChromeDriver(chromeOptions));
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
    }

    protected void destroyDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            drivers.remove();
        }
    }

    private void addDriver(WebDriver driver) {
        drivers.set(driver);
    }
}
