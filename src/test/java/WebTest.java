
import selenium.pages.AppointmentPage;
import selenium.pages.CreateProfilePage;
import selenium.pages.HomePage;
import selenium.pages.InsurancePage;
import selenium.utilities.TestHooks;
import org.testng.annotations.Test;

public class WebTest extends TestHooks {

    @Test(description = "Book an appointment")
    public void bookAnAppointment() {

        new HomePage(getDriver()).get().clickAppointment();

        new InsurancePage(getDriver()).get().clickPayForSelf();

        new AppointmentPage(getDriver()).get().pickDate(2).pickDoctorBasedOnRating("4.8");

        new CreateProfilePage(getDriver()).get();
    }
}
