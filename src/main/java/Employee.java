/**
 * Created by user on 14.12.17.
 */
public class Employee {

    private String departmentId;
    private String chiefId;
    private String name;
    private String salary;

    Employee(String departmentId, String chiefId, String name, String salary) {
        this.departmentId = departmentId;
        this.chiefId = chiefId;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return (departmentId != null ? departmentId.equals(employee.departmentId) : employee.departmentId == null) && (chiefId != null ? chiefId.equals(employee.chiefId) : employee.chiefId == null) && (name != null ? name.equals(employee.name) : employee.name == null) && (salary != null ? salary.equals(employee.salary) : employee.salary == null);
    }

    @Override
    public int hashCode() {
        int result = departmentId != null ? departmentId.hashCode() : 0;
        result = 31 * result + (chiefId != null ? chiefId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "departmentId='" + departmentId + '\'' +
                ", chiefId='" + chiefId + '\'' +
                ", name='" + name + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}
