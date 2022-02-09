package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.testng.Reporter;
import selenium.actions.Actions;

import java.util.List;

public class AppointmentPage extends BasePage<AppointmentPage> {

    private static final String XPATH_APPOINTMENT_WRAPPER = "//*[@id='appointments-wrapper']";
    private static final String XPATH_APPOINTMENT_DETAILS = "//*[@id='appointments-wrapper']/*[@class='appointment-list css-1lw3ul3 eaqau3j1']/*[@class='appointment css-11pcgvt eamk8rf6']/*[@class='columns is-multiline is-vcentered']/*[@class='column is-three-fifths-mobile is-half-desktop']/*[@class='columns is-multiline is-vcentered']/*[@class='css-1bnkrzn eamk8rf0 column']";
    private static final String XPATH_APPOINTMENT_BUTTONS = "//button[contains(@class, 'book-button css-37e0w1 e12r4e830')]";
    private static final String XPATH_CURRENT_DATE = "//*[contains(@class, 'react-datepicker__day--selected')]";
    private static final String CSS_DATE_PICKER = "[id='date-picker-input']";
    private static final String DATE_SELECTION = "react-datepicker__day--0";
    private static final String XPATH_RATING = "//*[@class='css-cjt61t eamk8rf1']";
    private static final String XPATH_APPOINTMENT_RATINGS = "//*[@id=\"appointments-wrapper\"]/div[2]/div[3]/div/div[2]/div/div[1]/div[1]/span";

    @FindBy(how = How.XPATH, using = XPATH_APPOINTMENT_WRAPPER)
    private WebElement appointmentWrapper;

    @FindBy(how = How.XPATH, using = XPATH_APPOINTMENT_DETAILS)
    private List<WebElement> appointmentDetails;

    @FindBy(how = How.CSS, using = CSS_DATE_PICKER)
    private WebElement datePicker;

    @FindBy(how = How.XPATH, using = XPATH_CURRENT_DATE)
    private WebElement currentDate;

    @FindBy(how = How.XPATH, using = XPATH_APPOINTMENT_BUTTONS)
    private List<WebElement> appointmentButtons;

    @FindBy(how = How.XPATH, using = XPATH_APPOINTMENT_RATINGS)
    private List<WebElement> appointmentRatings;

    public AppointmentPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertTrue(Actions.isDisplayed(appointmentWrapper));
    }

    public AppointmentPage pickDate(int days) {
        Actions.clickElement(datePicker);
        int target = Integer.parseInt(currentDate.getText()) + days;

        String targetXpath;
        if (target < 10) {
            targetXpath = DATE_SELECTION + "0" + target;
        } else {
            targetXpath = DATE_SELECTION + target;
        }

        WebElement targetDate = Actions.findByXpath("//*[contains(@class, '" + targetXpath + "')]");
        Actions.clickElement(targetDate);
        Reporter.log("Picked target date: " + targetDate);
        Actions.waitForPageLoad();

        return this;
    }

    public AppointmentPage pickDoctorBasedOnRating(String targetRating) {
        Actions.implicitWait();
        int index = 0;
        for (WebElement appointment : appointmentDetails) {
            String rating = appointment.findElement(new By.ByXPath(XPATH_RATING)).getText();
            if (Double.parseDouble(rating) >= Double.parseDouble(targetRating)) {
                Actions.clickElement(appointmentButtons.get(index));
                Reporter.log("Appointment details are: " + appointment.getText(), true);
                break;
            }
            index++;
        }

        return this;
    }
}
