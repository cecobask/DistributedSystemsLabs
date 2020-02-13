import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeService {

    private final EmployeeDaoImplementation employeeDaoImplementation = new EmployeeDaoImplementation();

    public void addEmployee(Employee employee) throws SQLException {
        employeeDaoImplementation.addEmployee(employee);
    }

    public Employee getEmployeeBySSN(String ssn) {
        return employeeDaoImplementation.getEmployeeBySSN(ssn);
    }

    public ArrayList<Employee> getAllEmployees() {
        return employeeDaoImplementation.getAllEmployees();
    }

    public boolean updateEmployee(Employee employee) {
        return employeeDaoImplementation.updateEmployee(employee);
    }

    public boolean deleteEmployee(String ssn) {
        return employeeDaoImplementation.deleteEmployee(ssn);
    }
}
