package selenium.actions;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import static selenium.utilities.DriverFactory.getDriver;

@UtilityClass
public class Actions {

    private final WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));

    public void clickElement(WebElement element) {
        try {
            scrollIntoView(element);
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (ElementClickInterceptedException | TimeoutException | NoSuchElementException e) {
            jsClick(element);
        }
    }

    public void jsClick(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", element);
    }

    public void inputElement(WebElement element, String input) {
        scrollIntoView(element);
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(input);
    }

    private void scrollIntoView(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].scrollIntoView(true)", element);
    }

    public String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    public void selectDropdown(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public boolean isDisplayed(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.isDisplayed();
    }

    public boolean isDisplayedIgnoringException(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (NoSuchElementException | TimeoutException exception) {
            return false;
        }
    }

    public void iFrameInputElement(WebElement iFrame, WebElement element, String input) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iFrame));
        inputElement(element, input);
        getDriver().switchTo().parentFrame();
    }

    public WebElement findByXpath(String xPath) {
        return getDriver().findElement(new By.ByXPath(xPath));
    }


    public boolean waitForPageLoad() {

        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            try {
                return ((Long) ((JavascriptExecutor) getDriver()).executeScript("return jQuery.active") == 0);
            } catch (Exception e) {
                return true;
            }
        };

        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) getDriver()).executeScript("return document.readyState")
                .toString().equals("complete");

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    public void implicitWait() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
}