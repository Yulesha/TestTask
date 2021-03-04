package definitions;

import com.GeneralMethods;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {

    @Before
    public void beforeTest() {
        GeneralMethods.createDriver();
    }

    @After
    public void afterTest() {
        WebDriver driver = GeneralMethods.getDriver();
        if (driver != null) driver.close();
    }
}
