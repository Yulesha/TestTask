import com.google.gson.JsonArray;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by user on 18.09.17.
 */

@Title("Tests")
public class Tests {

    @Title("Test case 1")
    @Test
    public void test_Test_Case_1() throws InterruptedException, IOException, SQLException, ClassNotFoundException {

        Connection con = DBMethods.connectToDB();
        ResultSet queryResult = DBMethods.doQuery(con);
        ArrayList<Employee> employeesDB = DBMethods.getEmployeesFromDB(con, queryResult);
        for (Employee x:employeesDB
             ) {
            System.out.println(x);
        }
        WebDriver driver = GeneralMethods.createDriver();
        Page page = new Page(driver);
        page.openPage();
        System.out.println("");
        ArrayList<Employee> employeesUI = page.getEmployeesFromUI();
        for (Employee x:employeesUI
                ) {
            System.out.println(x);
        }
        page.compareResults(employeesDB, employeesUI, queryResult);
        driver.close();
    }



    @Title("Test case 2")
    @Test
    public void test_Test_Case_2() throws InterruptedException, IOException {
        HttpURLConnection con = RequestMethods.sendRequest();
        RequestMethods.checkCode(con);
        String result = RequestMethods.checkResponse(con);
        RequestMethods.validationJson(result);
    }

}
