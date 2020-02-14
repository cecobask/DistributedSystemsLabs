import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDao {

    void addEmployee(Employee employee) throws SQLException;

    Employee getEmployeeBySSN(String ssn);

    ArrayList<Employee> getAllEmployees();

    boolean updateEmployee(Employee employee);

    boolean deleteEmployee(String ssn);

}
