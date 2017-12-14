import ru.yandex.qatools.allure.annotations.Step;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by user on 14.09.17.
 */
class DBMethods {

    @Step("Connect to DB")
    static Connection connectToDB()  {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "testcandidate");
        connectionProps.put("password", "Ej7mhonxAdHpNoNv");
        try {
            conn = DriverManager.getConnection("jdbc:mysql://188.166.161.108:3306", connectionProps);
            System.out.println("Connected to database");

        }
        catch (SQLException e){
            GeneralMethods.saveErrorLog("Connection is failed");

        }
        return conn;
    }

   static ResultSet doQuery(Connection con) {
        ResultSet select = null;
        try{
            Statement st = con.createStatement();
            select = st.executeQuery("SELECT * FROM candidate.EMPLOYEE");
        }
        catch (SQLException s){
            GeneralMethods.saveErrorLog("SQL statement is not executed!");
        }
        return select;
    }

    @Step("Get employees from DB")
    static ArrayList<Employee> getEmployeesFromDB(ResultSet queryResult) {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        try {
            while (queryResult.next()) {
                employees.add(new Employee(queryResult.getString("DEPARTMENT_ID"),
                        queryResult.getString("CHIEF_ID"),
                        queryResult.getString("NAME"),
                        queryResult.getString("SALARY")));
            }
        }
        catch (SQLException e){
            GeneralMethods.saveErrorLog("Connection is failed");
        }
        return employees;
    }

}
