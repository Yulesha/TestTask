package definitions;

import com.GeneralMethods;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void beforeTest() {
        GeneralMethods.createDriver();
    }

    @After
    public void afterTest() {
        GeneralMethods.getDriver().close();
    }
}
