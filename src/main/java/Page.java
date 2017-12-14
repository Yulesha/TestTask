import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 18.09.17.
 */
class Page {

    private WebDriver driver;
    Page(WebDriver driver) {
       super();
       PageFactory.initElements(driver, this);
       this.driver = driver;
    }

    @Step("Open web page")
    void openPage() {
        driver.get("http://benzoelectromoty.ga/qa/");
    }

    @Step("compare DB data VS UI data")
    void compareResults(ArrayList<Employee> employeesDB, ArrayList<Employee> employeesUI, ResultSet queryResult) {
        try {
            compareHeaders(queryResult);
            compareRows(employeesDB, employeesUI);
        }
        catch (SQLException e){
            GeneralMethods.saveErrorLog("Connection is failed");
        }
    }

    private void compareHeaders(ResultSet queryResult) throws SQLException {
        int columnCount = queryResult.getMetaData().getColumnCount();
        List<WebElement> headers = driver.findElements(By.xpath("//th"));
        List<String> headersName = new ArrayList<String>();
        for (WebElement ele1 : headers) {
            headersName.add(ele1.getText());
        }
        for (int i=2; i <= columnCount; i++){
            Assert.assertTrue(headersName.contains(queryResult.getMetaData().getColumnName(i)));
        }
    }

    private void compareRows(ArrayList<Employee> employeesDB, ArrayList<Employee> employeesUI) throws SQLException {
        Assert.assertTrue(employeesUI.containsAll(employeesDB));
    }

    @Step("Get employees from table")
    ArrayList<Employee> getEmployeesFromUI() {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        List<WebElement> rows = driver.findElements(By.xpath("//tbody//tr"));
        for (int i=0; i<rows.size()-1; i++) {
            employees.add(new Employee(rows.get(i).findElement(By.xpath("./td[2]")).getText(),
                    rows.get(i).findElement(By.xpath("./td[3]")).getText(),
                    rows.get(i).findElement(By.xpath("./td[4]")).getText(),
                    rows.get(i).findElement(By.xpath("./td[5]")).getText()));

        }
        return employees;
    }
}
