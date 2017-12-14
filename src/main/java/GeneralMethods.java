import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.qatools.allure.annotations.Attachment;


import java.util.concurrent.TimeUnit;


/**
 * Created by user on 15.03.2017.
 */
class GeneralMethods {

    static WebDriver createDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        return driver;
    }

    @Attachment(value = "Error", type = "text/plain")
    static String saveErrorLog(String error){

        return error;
    }
}


