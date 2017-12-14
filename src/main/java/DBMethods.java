import ru.yandex.qatools.allure.annotations.Step;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by user on 14.09.17.
 */
class DBMethods {

    @Step("Connect to DB")
    static Connection connectToDB() throws ClassNotFoundException, SQLException {
        Connection conn;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "testcandidate");
        connectionProps.put("password", "Ej7mhonxAdHpNoNv");

        conn = DriverManager.getConnection("jdbc:mysql://188.166.161.108:3306", connectionProps);
        System.out.println("Connected to database");
        return conn;
    }

   static ResultSet doQuery(Connection con) throws SQLException {
        ResultSet select = null;
        try{
            Statement st = con.createStatement();
            select = st.executeQuery("SELECT * FROM candidate.EMPLOYEE");
        }
        catch (SQLException s){
            System.out.println("SQL statement is not executed!");
        }
        return select;
    }

    @Step("Get employees from DB")
    static ArrayList<Employee> getEmployeesFromDB(Connection con, ResultSet queryResult) throws SQLException {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        while (queryResult.next()){
            employees.add(new Employee(queryResult.getString("DEPARTMENT_ID"),
                    queryResult.getString("CHIEF_ID"),
                    queryResult.getString("NAME"),
                    queryResult.getString("SALARY")));
        }
        return employees;
    }

}
